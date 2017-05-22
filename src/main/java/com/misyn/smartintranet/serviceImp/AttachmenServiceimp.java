package com.misyn.smartintranet.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.AttachmentDAO;
import com.misyn.smartintranet.entity.Attachment;
import com.misyn.smartintranet.service.AttachmentService;

@Service
@Transactional
public class AttachmenServiceimp implements AttachmentService {
	
	@Autowired
	AttachmentDAO ad;

	@Override
	public Attachment attchmentgetByID(String id) {
		
		return ad.attchmentgetByID(id);
	}

}
