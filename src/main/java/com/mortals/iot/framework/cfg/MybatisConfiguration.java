package com.mortals.iot.framework.cfg;

import java.io.IOException;
import java.net.URLDecoder;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

//@Configuration
public class MybatisConfiguration {
    private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

    //  配置类型别名
    @Value("${spring.application.name}")
    private String name;
    //  配置类型别名
    @Value("${mybatis.root-path}")
    private String rootPath;
    private static final String ROOT_PATH_SPLIT = ",";

    //  配置类型别名
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //  加载全局的配置文件
    @Value("${mybatis.config-location}")
    private String configLocation;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    
    private final String PATH_SEPARATOR = "/";

    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
        	//解决myBatis下 不能从嵌套jar文件中读取class的问题
        	VFS.addImplClass(SpringBootVFS.class);
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(getTypeAliasesPackage());
            //
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //      //
            sessionFactoryBean.setConfigLocation(
                    new DefaultResourceLoader().getResource(configLocation));

            //添加插件  （改为使用配置文件加载了）
            //          sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error", e);
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error", e);
            return null;
        }
    }

    public String getTypeAliasesPackage() {
        if (StringUtils.isEmpty(typeAliasesPackage)) {
            return "";
        }
        rootPath = rootPath.trim().replace(".", PATH_SEPARATOR);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        StringBuffer typeAliasesPackageStringBuffer = new StringBuffer();
        try {
            for (String location : typeAliasesPackage.split(ROOT_PATH_SPLIT)) {
                if (StringUtils.isEmpty(location)) {
                    continue;
                }
                if (location.contains("*")) {
                    location = "classpath*:" + location.trim().replace(".", PATH_SEPARATOR);
                    location = getResources(resolver, location);
                }
                if(location.endsWith(PATH_SEPARATOR))
                {
                	location = location.substring(0, location.length() - 1);
                }
                typeAliasesPackageStringBuffer.append(location + ROOT_PATH_SPLIT);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        if (typeAliasesPackageStringBuffer.length() == 0) {
            throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误！请检查applicationContext.xml@sqlSessionFactory配置！");
        }
        String allTypeAliasesPackage = typeAliasesPackageStringBuffer.toString().replace(PATH_SEPARATOR, ".");
        logger.info("allTypeAliasesPackage:"+allTypeAliasesPackage);
        return allTypeAliasesPackage;
    }

    private String getResources(ResourcePatternResolver resolver, String location) throws IOException {
        StringBuffer resourcePathStringBuffer = new StringBuffer();
        for (Resource resource : resolver.getResources(location)) {
            if(resource == null || resource.getURL() == null)
            {
            	continue;
            }
            String path = resource == null ? "" : resource.getURL().getPath();
            path = URLDecoder.decode(path, "UTF-8");//对URL进行解码
            path = path.replaceAll("\\\\", PATH_SEPARATOR);//将所有反斜杠（\）替换成正斜杠（/）
            if (StringUtils.isEmpty(path) || path.indexOf(rootPath) == -1) {
                continue;
            }
            if(path.endsWith(PATH_SEPARATOR))
            {
            	path = path.substring(0, path.length() - 1);
            }
            resourcePathStringBuffer.append(path.substring(path.indexOf(rootPath))).append(ROOT_PATH_SPLIT);
        }
        return resourcePathStringBuffer.toString();
    }

}
