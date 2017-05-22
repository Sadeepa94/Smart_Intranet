package com.misyn.smartintranet.service;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Role;

public interface RoleService {
	
	public List<Role>  getAllRoles();
	public Role getRole(int id);
	public boolean updateRole(Role role);
	public boolean saveRole(Role role);
	public boolean deleteRole(int id);
	public List<Tuple> getAllRolesNameId();
	public Role getRoleByname(String roleName);

}
