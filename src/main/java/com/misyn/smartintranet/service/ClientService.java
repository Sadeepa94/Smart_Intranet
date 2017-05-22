package com.misyn.smartintranet.service;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Client;

public interface ClientService {
	
	public Client getClient(String id);
	public Client getClientByUsername(String userName);
	public boolean saveClient(Client client);
	public boolean updateClient(Client client);
	public boolean deleteClient(String id);
	public List<Tuple> getAllClient(String username);
	public String getNextClientUsername();
}
