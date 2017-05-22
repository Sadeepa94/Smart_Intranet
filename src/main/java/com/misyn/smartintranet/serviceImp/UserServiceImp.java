package com.misyn.smartintranet.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.misyn.smartintranet.dao.UserDAO;
import com.misyn.smartintranet.entity.User;
import com.misyn.smartintranet.service.UserService;



@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id);
	}
	
	

	@Override
	public User getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return userDAO.getUserByUsername(userName);
	}



	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDAO.saveUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		
		return userDAO.deleteUser(id);
	}
	
	public String getPassWordByUsernam(String username)
	{
		return userDAO.getPassWordByUsername(username);
	}



	@Override
	public Integer getIDByUsername(String username) {
		return userDAO.getIDByUsername(username);
	}



	@Override
	public boolean updateUser(User user) {
		
		return userDAO.updateUser(user);
	}



	@Override
	public List<String> getAllusernamesByroleName(String rolename) {
		
		return userDAO.getAllusernamesByroleName(rolename);
	}
	
	
	

}
