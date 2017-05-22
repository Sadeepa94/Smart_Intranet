package com.misyn.smartintranet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.FAQ;
import com.misyn.smartintranet.service.FaqService;
import com.misyn.smartintranet.util.JsonResponse;

@Controller
public class FaqController {
	
	@Autowired
	Gson gson;
	
	
	@Autowired
	FaqService faqservice;
	@RequestMapping(value="/faq", method=RequestMethod.GET)
	public String faqpage( Model model){
		
		
		List<FAQ> faqlist=faqservice.getAllFAQ();
		model.addAttribute("faqlist",faqlist );
		
		return "faq";
		
	}
	
	
	@RequestMapping(value="/savefaq", method=RequestMethod.GET)
	public @ResponseBody String saveorupdateFAQ(@Valid @ModelAttribute("faq") FAQ faq, BindingResult result){
		String s;
		System.out.println(faq.getFaqID()+"*********");
		if(result.hasErrors())
			 s ="fail";
		else
		{
			if(faqservice.saveFAQ(faq))
				s ="success";
			else
				s ="fail";
				
			
			
		}
		
		
		String aa=gson.toJson(s);
		return aa;
	}
	
	@RequestMapping(value="/loadfaq", method=RequestMethod.GET)
	public @ResponseBody List<FAQ> faq( Model model){
		
		
		List<FAQ> faqlist=faqservice.getAllFAQ();
		
			return faqlist;
		
	}
	
	@RequestMapping(value="/deletefaq/{faqID}", method=RequestMethod.GET)
	public @ResponseBody JsonResponse  deletefaq(@PathVariable("faqID") int faqID){
		JsonResponse js=new JsonResponse();
		
		faqservice.deleteFAQ(faqID);
		js.setStatus("successfully deleted faq");
		
		return js;
		
	}
	
	@RequestMapping(value="/getfaq/{faqID}", method=RequestMethod.GET)
	public @ResponseBody FAQ  getfaq(@PathVariable("faqID") int faqID){
		
		
		FAQ faq=faqservice.getFAQ(faqID);
		
		return faq;
		
	}


}
