package com.misyn.smartintranet.util;

import java.util.List;

import javax.persistence.Tuple;

public class PageList {
	
	private List<Tuple> source;
	private int pages;
	private int current;
	private int size;
	private boolean isFirstpage;
	private boolean isLastpage;
	
	
	public List<Tuple> getSource() {
		return source;
	}
	public void setSource(List<Tuple> source) {
		this.source = source;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isFirstpage() {
		return isFirstpage;
	}
	public void setFirstpage(boolean isFirstpage) {
		this.isFirstpage = isFirstpage;
	}
	public boolean isLastpage() {
		return isLastpage;
	}
	public void setLastpage(boolean isLastpage) {
		this.isLastpage = isLastpage;
	}
	
	

}
