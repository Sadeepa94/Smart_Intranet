package com.misyn.smartintranet.util;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class JsonResponse {
	
	 
	
	@Autowired
	MessageSource messages;

	
	private String status;
	private boolean success;
	private Map<String,String> errors = new HashMap<String,String>();
	
	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Map<String, String> getErrors() {
		return errors;
	}



	public void setErrors(BindingResult result) {
		List<FieldError> fielderrors=result.getFieldErrors();
		for(FieldError fielderror:fielderrors)
		{
			String m;
			try{
				
				
			m=messages.getMessage(fielderror.getCodes()[0],null, Locale.getDefault());
			
			}
			catch(Exception e){
				
				
				m=fielderror.getDefaultMessage();
				e.printStackTrace();
			}
			this.errors.put(fielderror.getObjectName()+fielderror.getField(), m);
		}
		
		
	}
	
	



	public boolean getSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}

	

}
