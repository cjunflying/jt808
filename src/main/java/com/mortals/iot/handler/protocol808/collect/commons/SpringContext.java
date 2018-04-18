package com.mortals.iot.handler.protocol808.collect.commons;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <li>文件名称: SpringContext</li> <li>文件描述: $</li> <li>内容摘要: 包括模块、函数及其功能的说明</li>
 * <li>完成日期：2017-12-31</li> <li>修改记录1:user</li>
 * 
 */
public class SpringContext implements ApplicationContextAware, DisposableBean
{

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); //NOSONAR


	/**
	 * 实现DisposableBean接口,在Context关闭时清理静态变量.
	 */
	public void destroy() throws Exception
	{
		SpringContext.clear();
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name)
	{
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType)
	{
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 清除SpringContextUtils中的ApplicationContext为Null.
	 */
	public static void clear()
	{
		applicationContext = null;
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected()
	{
		if (applicationContext == null)
		{
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtils");
		}
	}

	/* (non-Javadoc)  
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)  
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException
	{
		// TODO Auto-generated method stub

	}
}
