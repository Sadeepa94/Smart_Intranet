package com.misyn.smartintranet.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.ClientDAO;
import com.misyn.smartintranet.dao.RoleDAO;
import com.misyn.smartintranet.entity.Client;
import com.misyn.smartintranet.entity.Role;
import com.misyn.smartintranet.service.ClientService;
import com.misyn.smartintranet.util.NextValueGen;

@Service
@Transactional
public class ClientServiceimp implements ClientService {
	
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Client getClient(String id) {
		return clientDAO.getClient(id);
	}

	@Override
	public Client getClientByUsername(String userName) {
		
		return clientDAO.getClientByUsername(userName);
	}

	@Override
	public boolean saveClient(Client client) {
		client.setPassword(passwordEncoder.encode("mi_syn"));
		Role role=roleDAO.getRoleByname("client");
		client.setRole(role);
		return clientDAO.saveClient(client);
	}

	@Override
	public boolean deleteClient(String id) {
		
		return clientDAO.deleteClient(id);
	}

	@Override
	public List<Tuple> getAllClient(String username) {
		return clientDAO.getAllClient(username);
	}

	@Override
	public boolean updateClient(Client client) {
		return clientDAO.updateClient(client);
	}

	@Override
	public String getNextClientUsername() {
		String lastUsername=clientDAO.getLastClientUsername();
		if(lastUsername==null)
			return "cli0001";
		else
			return NextValueGen.getNextValue(lastUsername);

	}

	
	
}
