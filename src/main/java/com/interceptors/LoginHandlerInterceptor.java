package com.interceptors;


import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.models.User;

/**
 * @author Behrooz
 *
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter  {
	
	private String getUsernameFromCookie(HttpServletRequest request) {
		for(Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("mobile")) {
				return cookie.getValue();
			}
		}
		return null;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
//		List<User> userInfo = getUsersFromDB("WHERE `mobile` = '" + user.getMobile() + "' ");
		String mobile = getUsernameFromCookie(request);
		if(mobile != null) {
			ModelMap model = new ModelMap();
			response.sendRedirect("/Parking");
			return false;
		}
		return true;	
	}	 
		

}