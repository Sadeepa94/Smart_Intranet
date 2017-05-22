package com.misyn.smartintranet.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Reply;

public interface ReplyDAO {
	
	
	public boolean updateReply(Reply reply);
	public boolean saveReply(Reply reply);
	public boolean deleteReply(long id);
	public List<Tuple>  getAllRepliesByQuestionID(long QuestionID);
	public List<Tuple>  getAllAttachmentByReply(long id);
	public List<Tuple> getAllvisibleRepliesByQuestionID(long questionID);

	

}
