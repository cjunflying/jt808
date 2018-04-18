package com.mortals.iot.handler.protocol808.collect.commons;

public class MyTest {

	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
		   if (src == null || src.length <= 0) {  
		        return null;  
		    }  
		    for (int i = 0; i < src.length; i++) {  
		        int v = src[i] & 0xFF;  
		        String hv = Integer.toHexString(v);  
		        if (hv.length() < 2) {  
		            stringBuilder.append(0);  
		        }  
		        stringBuilder.append(hv);  
		    }  
		    return stringBuilder.toString();  
		}  

	
	public static byte[] hexStringToBytes(String hexString) {  
		    if (hexString == null || hexString.equals("")) {  
		       return null;  
		   }  
		    hexString = hexString.toUpperCase();  
		    int length = hexString.length() / 2;  
		   char[] hexChars = hexString.toCharArray();  
		    byte[] d = new byte[length];  
		    for (int i = 0; i < length; i++) {  
		        int pos = i * 2;  
		       d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
		    }  
		   return d;  
		}  
	
	 public static byte charToByte(char c) {  
		    return (byte) "0123456789ABCDEF".indexOf(c);  
		}  


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte b[]={0x7E, 00 ,02 };
		System.out.println(bytesToHexString(b));
		
		System.out.println(hexStringToBytes("126"));
	}	

}
