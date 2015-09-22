package com.webapp;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CookieBox{
	
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	public CookieBox(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies !=null){
			for(int i=0; i<cookies.length; i++){
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}
	
	public static Cookie createCookie(String name, String value)
		throws IOException{
		return new Cookie(name, URLEncoder.encode(value, "euc-kr"));
	}
	
	public static Cookie createCookie(String name, String value, String path, int maxAge)
			throws IOException{
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
			cookie.setPath(path);
			cookie.setMaxAge(maxAge);
			return cookie;
		}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge)
			throws IOException{
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
			cookie.setDomain(domain);
			cookie.setPath(path);
			cookie.setMaxAge(maxAge);
			return cookie;
		}
	
	public Cookie getCookie(String name){
		return cookieMap.get(name);
		}
	
	public String getValue(String name)
			throws IOException{
			Cookie cookie = cookieMap.get(name);
			if(cookie==null){
				return null;
			}
			return URLDecoder.decode(cookie.getValue(), "euc-kr");
		}
	
	public boolean exists(String name){
		return cookieMap.get(name)!= null;
	}
	
}
