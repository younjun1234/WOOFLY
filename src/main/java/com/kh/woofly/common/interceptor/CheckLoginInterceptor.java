package com.kh.woofly.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 // @Override라서 매개변수 직접 추가하고 빼는거 절대!!! 안됨! 
	      // preHandle은 컨트롤러 넘어가기 전이라 얘만 타입이 boolean임 return을 가지고서 넘길지 말지 정해짐
	      HttpSession session = request.getSession();
	      Member loginUser = (Member)session.getAttribute("loginUser");
	      if(loginUser == null) {
	         String url = request.getRequestURI();
	         String msg = null;
	         if(url.contains("my")) {
	            msg = "로그인 후 이용하세요";
	         }else {
	            msg="로그인 세션이 만료되어 로그인 화면으로 넘어갑니다.";
	         }
	         
	         response.setContentType("text/html; charset=UTF-8");
	         response.getWriter().write("<script>location.href='/account/login';</script>");
	         
	         return false; //세션만료되었으면 login 화면으로 넘어가지 selectBoard.bo .at으로 넘어가면 안되기 때문에 false 반환
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
		//무조건 true 반환
	}
}
	
	
