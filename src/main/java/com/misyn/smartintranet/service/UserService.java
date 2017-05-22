package com.misyn.smartintranet.service;


import java.util.List;

import com.misyn.smartintranet.entity.User;

public interface UserService {
	public User getUser(int id);
	public User getUserByUsername(String userName);
	public boolean saveUser(User user);
	public boolean deleteUser(int id);
	public String getPassWordByUsernam(String username);
	public Integer getIDByUsername(String username);
	public boolean updateUser(User user);
	public List<String> getAllusernamesByroleName(String rolename);
}
