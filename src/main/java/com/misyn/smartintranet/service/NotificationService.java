package com.misyn.smartintranet.service;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Notification;

public interface NotificationService {
	public Notification getNotification(long id);
	public boolean saveNotification(Notification notification);
	public boolean updateNotification(Notification notification);
	public List<Tuple> getAllNotificationByUsername(String username);
	public List<Tuple> getAllNotificationByStatusAndUsername(String status,String username);


}
