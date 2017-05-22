package com.misyn.smartintranet.serviceImp;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.service.UserService;

@Service("userDetailsService") 
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.misyn.smartintranet.entity.User user=userService.getUserByUsername(username);
		
		if(user!=null){
		
		Collection<GrantedAuthority> autho=new ArrayList <GrantedAuthority>();
		autho.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		
		User newuser=new User(username, user.getPassword(),user.isEnable(),true,true,true, autho);
		
		return newuser;
		}
		else
		{
			System.out.println("username nt found");
			throw new UsernameNotFoundException("Invalid Username");
			
		}
	}

}
