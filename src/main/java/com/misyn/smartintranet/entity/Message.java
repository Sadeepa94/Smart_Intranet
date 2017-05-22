package com.misyn.smartintranet.entity;

public class Message {
	
	int no;
	String messesge;
	
	public Message(int i){
		this.no=1;
		this.messesge="new question recived";
	}
	
	public Message(int no, String status )
	{
		this.no=no;
		messesge="You have "+no+" "+status+" questions";
				
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMessesge() {
		return messesge;
	}

	public void setMessesge(String messesge) {
		this.messesge = messesge;
	}
	

}
