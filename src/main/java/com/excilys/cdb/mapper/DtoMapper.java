package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class DtoMapper<DTO,T> {
	
	/**
	 * Converts a list of computers into a list of computer DTOs.
	 * 
	 * @param computerList
	 *            List of computers to convert.
	 * @return List of computer DTOs.
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
	
	public abstract DTO createDto(T t);
}
