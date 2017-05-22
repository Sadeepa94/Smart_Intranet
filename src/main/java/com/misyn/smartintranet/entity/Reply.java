package com.misyn.smartintranet.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.misyn.smartintranet.util.ValidAttachment;

@Entity
@Table(name="Reply_info")
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long replyID;
	@NotEmpty
	private String replyDescription;
	private Date date;
	private boolean hidden;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Attachment> attachments;
	@ManyToOne( cascade=CascadeType.MERGE)
	private User user;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Question question;
	
	@ValidAttachment
	@Transient
	private List<MultipartFile> uploadings;
	
	
	
	public long getReplyID() {
		return replyID;
	}
	public void setReplyID(long replyID) {
		this.replyID = replyID;
	}
	public String getReplyDescription() {
		return replyDescription;
	}
	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser (User user) {
		this.user = user;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachment(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public List<MultipartFile> getUploadings() {
		return uploadings;
	}
	
	public void setUploadings(List<MultipartFile> uploadings) {
		this.uploadings = uploadings;
	}
	
	
	
	
	
	

}
