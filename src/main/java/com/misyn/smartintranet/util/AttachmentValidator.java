package com.misyn.smartintranet.util;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentValidator implements ConstraintValidator<ValidAttachment,List<MultipartFile>> {

	@Override
	public void initialize(ValidAttachment validAttachment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(List<MultipartFile> uploadings, ConstraintValidatorContext context) {
		 boolean result=false;
		 
		 if(uploadings==null)
		 	return true;
		 
		 else if(uploadings.size()==1&&uploadings.get(0).getContentType().equals("application/octet-stream"))
		{
			
			return true;
			
		}
		
		
		else
		{
			for(MultipartFile file : uploadings)
			{
				String type=FilenameUtils.getExtension(file.getOriginalFilename());
				System.out.println("none emptyfile");
				System.out.println(file.getSize());
				if(type.equals("pdf")||type.equals("png")||type.equals("jpeg")||type.equals("jpg")||type.equals("doc")||type.equals("docx")&&(file.getSize()!=0))
					result=true;
				else
					return false;
			}
			
			return result;
		}
		
		
		
	}

	

}
