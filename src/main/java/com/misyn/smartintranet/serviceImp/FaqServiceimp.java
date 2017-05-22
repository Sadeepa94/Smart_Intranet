package com.misyn.smartintranet.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.FaqDAO;
import com.misyn.smartintranet.entity.FAQ;
import com.misyn.smartintranet.service.FaqService;

@Transactional
@Service
public class FaqServiceimp implements FaqService{
	
	@Autowired
	FaqDAO faqDAO;

	@Override
	public FAQ getFAQ(int id) {
		return faqDAO.getFAQ(id);
	}

	@Override
	public boolean saveFAQ(FAQ faq) {
		return faqDAO.saveFAQ(faq);
	}

	@Override
	public boolean deleteFAQ(int id) {
		return faqDAO.deleteFAQ(id);
	}

	@Override
	public List<FAQ> getAllFAQ() {
		return faqDAO.getAllFAQ();
	}

}
