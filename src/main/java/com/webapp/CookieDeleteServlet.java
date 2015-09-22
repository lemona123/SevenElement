package com.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet(urlPatterns = {"/cookie/cookiedelete", "/cookie/cd"})
public class CookieDeleteServlet extends HttpServlet{
	
	static Log log = LogFactory.getLog(CookieDeleteServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	// 요청들어오는 것에 대해 utf-8로 인코딩
		response.setContentType("text/html; charset=utf-8");	// 문서타입; 그것에 대해 엔코딩된건 응답헤더로 들어감
		PrintWriter out = response.getWriter();
		
		ServletContext application = getServletContext();
		String context = application.getContextPath();
			
		Cookie c1 = new Cookie("id", URLEncoder.encode("홍길동 2", "utf-8"));
		c1.setMaxAge(0);
		c1.setPath(context + "/cookie");
		Cookie c2 = new Cookie("pw", "1234");
		c2.setMaxAge(0);
		c2.setPath(context + "/cookie");
		Cookie c3 = new Cookie("age", "30");
		c3.setMaxAge(0);
		c3.setPath(context + "/cookie");
		
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
		out.println("<hr>");
		out.println("<h1> 쿠키 셋팅 요청 ==> 헤더를 참조하세요</h1>");
		out.println("<hr>");
	}
	
}
