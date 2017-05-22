package com.misyn.smartintranet.serviceImp;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.NotificationDAO;
import com.misyn.smartintranet.entity.Notification;
import com.misyn.smartintranet.entity.User;
import com.misyn.smartintranet.service.NotificationService;
import com.misyn.smartintranet.service.UserService;

@Service
@Transactional
public class NotificationServiceimp implements NotificationService{
	
	@Autowired
	NotificationDAO notificationDAO;
	
	@Autowired
	UserService us;

	@Override
	public boolean saveNotification(Notification notification) {
		
		// TODO Auto-generated method stub
		notification.setDate(new Date());
		User user=us.getUser(notification.getFrom().getId());
		String message="you have reply from " +user.getUsername()+" at " +notification.getDate().toString();
		notification.setMessage(message);
		return notificationDAO.saveNotification(notification);
	}

	@Override
	public boolean updateNotification(Notification notification) {
		return notificationDAO.updateNotification(notification);
	}

	@Override
	public List<Tuple> getAllNotificationByUsername(String username) {
		return notificationDAO.getAllNotificationByUsername(username);
	}

	@Override
	public List<Tuple> getAllNotificationByStatusAndUsername(String status,String username) {
		return notificationDAO.getAllNotificationByStatusAndUsername(status, username);
	}

	@Override
	public Notification getNotification(long id) {
		return notificationDAO.getNotification(id);
	}
	
	

}
