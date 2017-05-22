package com.misyn.smartintranet.entity;

import javax.persistence.Embeddable;

@Embeddable

public class Address {
	
	private String no;
	private String Street;
	private String town;
	private String district;
	
	
	
	public String getNo() {

		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	

}
