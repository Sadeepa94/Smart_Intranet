package com.misyn.smartintranet.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.RoleDAO;
import com.misyn.smartintranet.entity.Role;
import com.misyn.smartintranet.service.RoleService;

@Service
@Transactional
public class RoleServiceimp implements RoleService {
	
	@Autowired
	RoleDAO roleDAO;

	@Override
	public List<Role> getAllRoles() {
		return roleDAO.getAllRoles();
	}

	@Override
	public Role getRole(int id) {
		return roleDAO.getRole(id);
	}

	@Override
	public boolean updateRole(Role role) {
		return roleDAO.updateRole(role);
	}

	@Override
	public boolean saveRole(Role role) {
		return roleDAO.saveRole(role);
	}

	@Override
	public boolean deleteRole(int id) {
		return roleDAO.deleteRole(id);
	}

	@Override
	public List<Tuple> getAllRolesNameId() {
		return roleDAO.getAllRolesNameId();
	}

	@Override
	public Role getRoleByname(String roleName) {
		// TODO Auto-generated method stub
		return roleDAO.getRoleByname(roleName);
	}

}
