package com.misyn.smartintranet.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Role;



public interface RoleDAO {
	
	public List<Role>  getAllRoles();
	public Role getRole(int id);
	public boolean updateRole(Role role);
	public boolean saveRole(Role role);
	public boolean deleteRole(int id);
	public List<Tuple> getAllRolesNameId();
	public Role getRoleByname(String roleName);

}
