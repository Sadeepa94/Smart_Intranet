package com.misyn.smartintranet.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="faq_info") 
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int faqID;
	@Column(length = 1000)
	private String questionDescription;
	@Column(length = 1000)
	private String answerDescription;
	
	
	public int getFaqID() {
		return faqID;
	}
	public void setFaqID(int faqID) {
		this.faqID = faqID;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getAnswerDescription() {
		return answerDescription;
	}
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}
	
	
	
	

	
	
}
