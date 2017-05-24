package com.excilys.cdb.webui.utility.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Page;

public abstract class AbstractDtoMapper<DTO,T> {
	
	/**
	 * Converts a page of elements into a list of element DTOs.
	 * 
	 * @param Page
	 *            Page of elements to convert.
	 * @return Page of element DTOs.
	 */
	public abstract Page<DTO> createDtoPage(Page<T> page);
	
	
	/**
	 * Converts a list of elements into a list of element DTOs.
	 * 
	 * @param List
	 *            List of elements to convert.
	 * @return List of element DTOs.
	 */
	public List<DTO> createDtoList(List<T> list) {
		List<DTO> dtoList = new ArrayList<>();

		for (T t : list) {
			if (t != null) {
				dtoList.add(createDto(t));
			}
		}

		return dtoList;
	}
	
	
	/**
	 * Converts a computer into a computer DTOs.
	 * 
	 * @param t
	 *            Element to convert.
	 * @return Element DTO.
	 */
	public abstract DTO createDto(T t);
}
