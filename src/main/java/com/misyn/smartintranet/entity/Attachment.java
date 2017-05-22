package com.misyn.smartintranet.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Attachment_info")
public class Attachment {
	
	@Id
	private String attachmentID;
	private String showingName;
	private String contentType;
	
	
	
	
	
	
	
	
	
	
	public String getAttachmentID() {
		return attachmentID;
	}
	public void setAttachmentID(String attachmentID) {
		this.attachmentID = attachmentID;
	}
	public String getShowingName() {
		return showingName;
	}
	public void setShowingName(String showingName) {
		this.showingName = showingName;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	
	
	

}
