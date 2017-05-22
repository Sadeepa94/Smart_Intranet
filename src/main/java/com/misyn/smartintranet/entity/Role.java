package com.misyn.smartintranet.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Role_info")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleID;
	private String roleName;
	private String roleDescription;
	
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> features=new HashSet<String>();
	
	@JsonIgnore 
	@OneToMany(cascade=CascadeType.ALL, mappedBy="role" ,fetch=FetchType.LAZY)
	private List<User> user=new ArrayList<User>();
	
		
	
	
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Collection<User> getUser() {
		return user;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	
	
	public Set<String> getFeatures() {
		return features;
	}
	public void setFeatures(Set<String> features) {
		this.features = features;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
	
	
	

}
