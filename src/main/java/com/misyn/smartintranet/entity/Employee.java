package com.misyn.smartintranet.entity;


import java.util.Date;


import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;





@Entity

public class Employee extends User{
	
	
	private String first_name;
	private String last_name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	private boolean availability;
	
	 @OneToOne(fetch = FetchType.EAGER)
	    private Type type;
	    private int remainingShortLeaves;
	    private int remainingAnnualLeaves;
	    private int remainingCasualLeaves;
	    private int remainingSickLeaves;
	
	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getRemainingShortLeaves() {
		return remainingShortLeaves;
	}
	public void setRemainingShortLeaves(int remainingShortLeaves) {
		this.remainingShortLeaves = remainingShortLeaves;
	}
	public int getRemainingAnnualLeaves() {
		return remainingAnnualLeaves;
	}
	public void setRemainingAnnualLeaves(int remainingAnnualLeaves) {
		this.remainingAnnualLeaves = remainingAnnualLeaves;
	}
	public int getRemainingCasualLeaves() {
		return remainingCasualLeaves;
	}
	public void setRemainingCasualLeaves(int remainingCasualLeaves) {
		this.remainingCasualLeaves = remainingCasualLeaves;
	}
	public int getRemainingSickLeaves() {
		return remainingSickLeaves;
	}
	public void setRemainingSickLeaves(int remainingSickLeaves) {
		this.remainingSickLeaves = remainingSickLeaves;
	}
	
	
	
	
	
	
	
	
	
	
}
