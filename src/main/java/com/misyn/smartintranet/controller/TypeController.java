package com.misyn.smartintranet.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misyn.smartintranet.entity.Type;
import com.misyn.smartintranet.service.TypeService;
import com.misyn.smartintranet.util.JsonResponse;

@Controller
public class TypeController {
	
	@Autowired
	TypeService typeService;
	
	@RequestMapping(value="/createType", method=RequestMethod.GET)
	public @ResponseBody JsonResponse createTpe(@Valid @ModelAttribute("type") Type type,BindingResult result){
		JsonResponse js=new JsonResponse();
		if(result.hasErrors())
		{
			js.setSuccess(false);
			js.setStatus("Plese check and resubmit");
		}
		
		else{
			Type existingtype=typeService.getTypeByTypeName(type.getTypeName());
			boolean res;
			if(existingtype!=null)
			{
				existingtype.setMaxAnnualLeaves(type.getMaxAnnualLeaves());
				existingtype.setMaxCasualLeaves(type.getMaxCasualLeaves());
				existingtype.setMaxSickLeaves(type.getMaxSickLeaves());
				existingtype.setMaxShortLeaves(type.getMaxShortLeaves());
				existingtype.setDescription(type.getDescription());
				res=typeService.updateType(existingtype);
				js.setStatus("sucessfully updated employee type");
			}
			else{
				res=typeService.saveType(type);
				js.setStatus("sucessfully create employee type");
			}
			
			
			if(res){
				js.setSuccess(true);
				
			}
			else{
				js.setSuccess(false);
				js.setStatus("Plese check and resubmit");
			}
		}
		
		
		
		return js;
	}
	
	
	@RequestMapping(value="/loadTypes", method=RequestMethod.GET)
	public @ResponseBody List<Type> loadTypes(){
		

		List<Type> types=typeService.getAllTypes();
		
		
		return types;
	}
	
	@RequestMapping(value="/getType/{id}", method=RequestMethod.GET)
	public @ResponseBody Type getTypes(@PathVariable("id") int id){
		

		Type type=typeService.getType(id);
		
		
		return type;
	}


}
