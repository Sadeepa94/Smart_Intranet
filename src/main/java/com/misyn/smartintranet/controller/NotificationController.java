package com.misyn.smartintranet.controller;

import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.misyn.smartintranet.entity.Message;
import com.misyn.smartintranet.entity.Notification;
import com.misyn.smartintranet.service.NotificationService;
import com.misyn.smartintranet.service.QuestionService;
import com.misyn.smartintranet.util.PageList;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService notiService;
	
	@Autowired
	QuestionService qs;
	
	@Autowired
	SimpMessagingTemplate sm;
	
	@Autowired
	Gson gson;

	
	@RequestMapping(value="/notificationread/{notificationID}", method=RequestMethod.GET)
	public @ResponseBody String maintenance(@PathVariable("notificationID") long notificationID,HttpServletRequest req){
		
		Notification notification=notiService.getNotification(notificationID);
		notification.setStatus("read");
		notiService.updateNotification(notification);
		String username=(String) req.getSession().getAttribute("username");;
		List<Tuple> notifications=notiService.getAllNotificationByStatusAndUsername("unread", username);
		String result=gson.toJson(notifications);
		 return result;
		
	}
	
	@RequestMapping(value="/notification", method=RequestMethod.GET)
	public @ResponseBody String notification(HttpServletRequest request){
		int id=(int) request.getSession().getAttribute("userId");
		String role=(String) request.getSession().getAttribute("role");
		Message message=null;
		if(role.equals("gateway controller"))
		{
			int i=qs.getNoOFquestionsstatus("new");
			if(i>0)
			{
				 message=new Message(i,"new");
				
			}
		}
		
		if(role.equals("tech supporter"))
		{
			int i=qs.getNoOFquestionsbyuserIDandstatus("inprogress", id);
			if(i>0)
			{
				 message=new Message(i,"inprogress");
				
			}
		}
		
		if(role.equals("client"))
		{
			int i=qs.getNoOFquestionsbyclientIDandstatus("inprogress", id);
			int j=qs.getNoOFquestionsbyclientIDandstatus("new", id);
			if(i+j>0)
			{
				 message=new Message(i+j,"inprogress and new");
				
			}
			
		}
		
		String result=gson.toJson(message);
		
		return result;
			
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAllnotifications/{place}", method=RequestMethod.GET)
	public @ResponseBody String getAllnotifications(@PathVariable("place") String place,HttpServletRequest req){
		
		
		
		String username=(String) req.getSession().getAttribute("username");
		PageList pl=new PageList();
		System.out.println(place);
		
		PagedListHolder<Tuple> notificationList = null;
		
		
		
		if(place.equals("view all")){
			System.out.println(place);
			notificationList = new PagedListHolder<Tuple>();
			List<Tuple> notifications=notiService.getAllNotificationByUsername(username);
			notificationList.setSource(notifications);
			notificationList.setPageSize(5);
			req.getSession().setAttribute("notilist", notificationList);
			pl.setSource(notificationList.getPageList());
			pl.setPages(notificationList.getPageCount());
			pl.setCurrent(notificationList.getPage()+1);
			pl.setFirstpage(notificationList.isFirstPage());
			pl.setLastpage(notificationList.isLastPage());	
		
        
		}
		
		else if(place.equals("more")){
			
			notificationList=(PagedListHolder<Tuple>) req.getSession().getAttribute("notilist");
			notificationList.nextPage();
			pl.setSource(notificationList.getPageList());
			pl.setPages(notificationList.getPageCount());
			pl.setCurrent(notificationList.getPage()+1);
			pl.setFirstpage(notificationList.isFirstPage());
			pl.setLastpage(notificationList.isLastPage());
		}
		
		else if(place.equals("prev")){
			
			notificationList=(PagedListHolder<Tuple>) req.getSession().getAttribute("notilist");
			notificationList.previousPage();
			pl.setSource(notificationList.getPageList());
			pl.setPages(notificationList.getPageCount());
			pl.setCurrent(notificationList.getPage()+1);
			pl.setFirstpage(notificationList.isFirstPage());
			pl.setLastpage(notificationList.isLastPage());
		}
		
		String result=gson.toJson(pl);
		 return result;
		
	}
	
	@RequestMapping(value="/getallunreadnotifictions", method=RequestMethod.GET)
	public @ResponseBody String question(HttpServletRequest req){
		
		String username=(String) req.getSession().getAttribute("username");;
		List<Tuple> notifications=notiService.getAllNotificationByStatusAndUsername("unread", username);
		
		String result =gson.toJson(notifications);
		System.out.println(result);
		 return result;
		
	}

}
