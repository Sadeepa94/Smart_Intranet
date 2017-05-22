package com.misyn.smartintranet.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;

import com.misyn.smartintranet.entity.User;
import com.misyn.smartintranet.service.UserService;

public class FirstLogin extends GenericFilterBean  {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService us;
	@Override
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String URL=null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean matche=false;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest=null;
		boolean isFirstLogin=true;
		
		if (request instanceof HttpServletRequest) {
			httpRequest=(HttpServletRequest)request;
			URL=((HttpServletRequest)request).getServletPath();
			try{
				isFirstLogin=(boolean) httpRequest.getSession().getAttribute("isFirstLogin");
			}
			catch(Exception e){
				isFirstLogin=true;
			}
		}
		
		
		
		if(auth!=null&&!URL.equals("/firstlogin.html")&&isFirstLogin)
		{
			String username=auth.getName();
			if(username.equals("admin"))
			{
				httpRequest.getSession().setAttribute("username", "admin");
				httpRequest.getSession().setAttribute("userId",0);
				List<String> features=new ArrayList<String>();
				features.add("maintenance");
				features.add("dashboards");
				features.add("ask question");
				features.add("details");
				features.add("question");
				httpRequest.getSession().setAttribute("features", features);
				httpRequest.getSession().setAttribute("role", "admin");
				chain.doFilter(request, response);
			}	
			else
			{
				User user= us.getUserByUsername(username);
				httpRequest.getSession().setAttribute("username", user.getUsername());
				httpRequest.getSession().setAttribute("userId", user.getId());
				List<String> features=new ArrayList<String>();
				features.addAll(user.getRole().getFeatures());
				
				httpRequest.getSession().setAttribute("features", features);
				
				
				httpRequest.getSession().setAttribute("role", user.getRole().getRoleName());
				matche=passwordEncoder.matches("mi_syn", us.getPassWordByUsernam(auth.getName()));
				if(matche){
					
					httpResponse.sendRedirect("/version2/firstlogin.html");
				}
				else{
					httpRequest.getSession().setAttribute("isFirstLogin", false);
					chain.doFilter(request, response);
				}
				
			}
			
			
		
		
			
		}
		
		else{
				chain.doFilter(request, response);
		}
		
		
		
		
		
		
	}

	


}
