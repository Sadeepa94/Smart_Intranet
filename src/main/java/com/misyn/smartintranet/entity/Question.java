package com.misyn.smartintranet.entity;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.misyn.smartintranet.util.ValidAttachment;
@Entity
@Table(name="Question_info")
public class Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionID;
	@Size(min=20 , max=100)
	private String topic;
	@NotEmpty
	private String questionDescription;
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int rating;
	
	
	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY ,cascade=CascadeType.MERGE)
	private Project project;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private Module module;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private Client client;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	private Employee technicalSuporter;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Attachment> attachment;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="question")
	private List<Reply> replies;
	
	private String category;
	
	
	@Transient
	@ValidAttachment
	@JsonIgnore
	private List<MultipartFile> uploadings;
	
	public Question( ) {
		
		
	}
	
	
	public long getQuestionID() {
		return questionID;
	}
	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Employee getTechnicalSuporter() {
		return technicalSuporter;
	}
	public void setTechnicalSuporter(Employee technicalSuporter) {
		this.technicalSuporter = technicalSuporter;
	}
	public List<Attachment> getAttachment(){
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public List<MultipartFile> getUploadings() {
		return uploadings;
	}
	public void setUploadings(List<MultipartFile> uploadings) {
		this.uploadings = uploadings;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

	
	
	
	
	

}
