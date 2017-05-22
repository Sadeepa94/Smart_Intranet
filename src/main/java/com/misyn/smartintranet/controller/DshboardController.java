package com.misyn.smartintranet.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.service.EmployeeService;
import com.misyn.smartintranet.service.QuestionService;

@Controller
public class DshboardController {
	
	@Autowired
	Gson gson;
	
	
	@Autowired
	QuestionService qservice;
	
	@Autowired
	EmployeeService eservice;
	

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard(){
		
		return "dashboard";
		
	}
	
	
	@RequestMapping(value="/getoverview", method=RequestMethod.GET)
	public @ResponseBody String getOverview(){
		
		
		List<Tuple> result=qservice.getCountByMonthWise();
		String response=gson.toJson(result);
		return response;
		
		
	}
	
	
	@RequestMapping(value="/getAllTechSupporters", method=RequestMethod.GET)
	public @ResponseBody String getAllTechSupporters(){
		
		List<Tuple> result=eservice.getEmployeesByRoleName("tech supporter");
		String response=gson.toJson(result);
		
		
		
		return response;
		
	}
	
	
	@RequestMapping(value="/getTechSupportPerform/{id}", method=RequestMethod.GET)
	public @ResponseBody List<Object> getTechSupportPerform(@PathVariable("id") int id){
		System.out.println(id);
		
		Employee tecsupport=eservice.getEmployee(id);
		int total=qservice.getNoOFquestionsbyuserIDandstatus("%", id);
		int close=qservice.getNoOFquestionsbyuserIDandstatus("close", id);
		int inprogress=qservice.getNoOFquestionsbyuserIDandstatus("inprogress", id);
		int furtherimplements=qservice.getNoOFquestionsbyuserIDandstatus("further implementation", id);
		Double rating=qservice.getTSRating(id)/5*100;
		
		List<Object> response=new ArrayList<Object>();
		response.add(tecsupport);
		response.add(total);
		response.add(close);
		response.add(inprogress);
		response.add(furtherimplements);
		response.add(rating);
		
		return response;
		
		
	}
	
	@RequestMapping(value="/gatoverallstatus", method=RequestMethod.GET)
	public @ResponseBody String gatoverallstatus(){
		
		List<Tuple> result=qservice.getCountByStatus();
		String response=gson.toJson(result);
		return response;
		
		
	}
	
	
	@RequestMapping(value="/getoverallstatusByDate", method=RequestMethod.GET)
	public @ResponseBody String gatoverallstatusByDate(@RequestParam("fromdate") String from, @RequestParam("todate") String to) throws ParseException{
		System.out.println("vvvvvvvvvvvvvvvvvv");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		List<Tuple> result=qservice.getCountByStatusAndperiod(f.parse(from), f.parse(to));
		String response=gson.toJson(result);
		return response;
		
		
	}
	
	@RequestMapping(value="/getoverallcategories", method=RequestMethod.GET)
	public @ResponseBody String gatoverallcategories(){
		
		List<Tuple> result=qservice.getCountByCategories();
		String response=gson.toJson(result);
		return response;
		
		
	}
	
	
	@RequestMapping(value="/getoverallcategoriesByDate", method=RequestMethod.GET)
	public @ResponseBody String gatoverallcategoriesByDate(@RequestParam("fromdate") String from, @RequestParam("todate") String to) throws ParseException{
		System.out.println("vvvvvvvvvvvvvvvvvv");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		List<Tuple> result=qservice.getCountByCategoriesAndperiod(f.parse(from), f.parse(to));
		String response=gson.toJson(result);
		return response;
		
		
	}
}
