package com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int numberOfElements;
	private int numberOfPages;
	private int pageNumber;
	private int pageSize;
	List<T> elementList;

	/* Default values for fields. */
	private final static int PAGE_NUMBER = 1;
	private final static int PAGE_SIZE = 10;

	
	/**
	 * Constructor for Page class.
	 */
	public Page() {
		pageNumber = PAGE_NUMBER;
		pageSize = PAGE_SIZE;
		
		elementList = new ArrayList<>();
	}
	

	/* Getters and setters */
	public int getNumberOfElements() {
		return this.numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	
	public int getNumberOfPages() {
		return this.numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0 && pageSize <= 100) {
			this.pageSize = pageSize;
		}
	}
	
	public List<T> getElementList() {
		return this.elementList;
	}

	public void setElementList(List<T> elementList) {
		this.elementList = elementList;
	}
}
