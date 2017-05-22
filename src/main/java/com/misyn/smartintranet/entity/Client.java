package com.misyn.smartintranet.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Client extends User {
	
	
	private String companyName;
	private String responsible_person;
	@OneToMany(cascade=CascadeType.MERGE ,mappedBy="client", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Project> project;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Question> questions;
	
	
	
	
	
	
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getResponsible_person() {
		return responsible_person;
	}
	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
	
	
	

	
}
