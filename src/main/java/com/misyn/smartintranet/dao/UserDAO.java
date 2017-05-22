package com.misyn.smartintranet.dao;


import java.util.List;



import com.misyn.smartintranet.entity.User;

public interface UserDAO {
	
	public User getUser(int id);
	public User getUserByUsername(String userName);
	public boolean saveUser(User user);
	public boolean deleteUser(int id);
	public String getPassWordByUsername(String Username);
	public Integer getIDByUsername(String Username);
	public boolean updateUser(User user);
	public List<String> getAllusernamesByroleName(String rolename);
	
	

}
