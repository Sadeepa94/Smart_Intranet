package com.misyn.smartintranet.service;

import java.util.List;

import com.misyn.smartintranet.entity.FAQ;

public interface FaqService {
	
	public FAQ getFAQ(int id);
	public boolean saveFAQ(FAQ faq);
	public boolean deleteFAQ(int id);
	public List<FAQ> getAllFAQ();

}
