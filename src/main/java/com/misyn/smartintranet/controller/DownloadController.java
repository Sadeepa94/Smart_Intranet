package com.misyn.smartintranet.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.misyn.smartintranet.entity.Attachment;
import com.misyn.smartintranet.service.AttachmentService;

@Controller
public class DownloadController {
	@Autowired
	AttachmentService as;
	
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void viewQuestion(@RequestParam("filename") String filename,HttpServletRequest request,HttpServletResponse response ){
		
		String location=("C:/mytemp/");
		Path file = Paths.get(location, filename);
		if(Files.exists(file)){
			Attachment a= as.attchmentgetByID(filename);
			if(a.getContentType()==null)
				System.out.println("null contenttype-------------------------------------------------");
			System.out.println(a.getContentType());
			 response.setContentType(a.getContentType());
			 response.addHeader("Content-Disposition","attachment; filename=\"" + a.getShowingName() +"\"");
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            } 
	            catch (IOException ex) {
	                ex.printStackTrace();
	            }
			
		}
		
		
		
		System.out.println(filename);
		
		
		
		
		
		
		
	}

}
