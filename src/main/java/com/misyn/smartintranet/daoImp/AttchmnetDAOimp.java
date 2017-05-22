package com.misyn.smartintranet.daoImp;






import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misyn.smartintranet.dao.AttachmentDAO;
import com.misyn.smartintranet.entity.Attachment;


@Repository
public class AttchmnetDAOimp implements AttachmentDAO {
	
	@Autowired
	SessionFactory session;
	

	@Override
	public Attachment attchmentgetByID(String id) {
		try{
			Attachment attachment=session.getCurrentSession().get(Attachment.class,id);
			return attachment;
		}
		catch(Exception ex){
			return null;
		}
	}

	
	
	

}
