package com.misyn.smartintranet.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.misyn.smartintranet.entity.Attachment;

public class Uploading {
	
	

	public List<Attachment> uploadfile(List<MultipartFile> files,HttpServletRequest request) 
	{
		List<Attachment> attachments=new ArrayList<Attachment>();
		String location=("C:/mytemp/");
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("YYYYMMddhhmmss");
		
		System.out.println(location);
		
		for(MultipartFile file:files)
		{
			
			String dateAsString = simpleDateFormat.format(new Date());
			
			File f;
			try {
				f = File.createTempFile(dateAsString,"."+FilenameUtils.getExtension(file.getOriginalFilename()), new File(location));
				FileCopyUtils.copy(file.getBytes(), f);
				
				
				Attachment attachment = new Attachment();
				attachment.setAttachmentID(f.getName());
				attachment.setShowingName(file.getOriginalFilename());
				attachment.setContentType(file.getContentType());
				attachments.add(attachment);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
		System.out.println(attachments.size()+"-----------==============-------------======");
		return attachments;
	}

}
