package com.shinhan.spring.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @WebFilter : 서블릿 3버전부터 지원. 하위버전은 web.xml을 이용해서 filter 등록
 * LoginChkFilter는 .do라는 요청 시에만 수행하도록 하기
 */
@WebFilter("*.do")
public class LoginCheckFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("LoginChkFilter 요청 주소 확인" + req.getRequestURI());

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
