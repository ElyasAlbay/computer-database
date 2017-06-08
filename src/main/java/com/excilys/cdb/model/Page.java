package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int numberOfElements;
	private int numberOfPages;
	private int pageNumber;
	private int pageSize;
	private String order;
	private List<T> elementList;

	/* Default values for fields. */
	private final static int PAGE_NUMBER = 1;
	private final static int PAGE_SIZE = 10;
	private final static String ORDER = "computer.id";

	
	/**
	 * Constructor for Page class.
	 */
	public Page() {
		this.pageNumber = PAGE_NUMBER;
		this.pageSize = PAGE_SIZE;
		this.order = ORDER;

		this.elementList = new ArrayList<>();
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
		this.pageSize = pageSize;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		if (order.equals("computerName")) {
			this.order = "computer.name";
		} else if (order.equals("companyName")) {
			this.order = "company.name";
		} else {
			this.order = order;
		}
	}

	public List<T> getElementList() {
		return this.elementList;
	}

	public void setElementList(List<T> elementList) {
		this.elementList = elementList;
	}
}
