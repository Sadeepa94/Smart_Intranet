package com.misyn.smartintranet.serviceImp;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.ReplyDAO;
import com.misyn.smartintranet.entity.Reply;
import com.misyn.smartintranet.service.ReplyService;

@Service
@Transactional
public class ReplyServiceimp implements ReplyService {
	
	@Autowired
	ReplyDAO replyDAO;

	@Override
	public boolean saveReply(Reply reply) {
		reply.setDate(new Date());
		return replyDAO.saveReply(reply);
		
	}

	
}
