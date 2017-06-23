package com.excilys.cdb.binding;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.User;
import com.excilys.cdb.model.dto.UserDto;

public class UserMapper {
	
	/**
	 * Converts a list of users into a list of user DTOs.
	 * 
	 * @param list
	 *            List of users to convert.
	 * @return List of user DTOs.
	 */
	public static List<UserDto> createDtoList(List<User> list) {
		List<UserDto> dtoList = new ArrayList<>();

		for (User u : list) {
			if (u != null) {
				dtoList.add(createDto(u));
			}
		}

		return dtoList;
	}
	
	/**
	 * Converts a user into a user DTO.
	 * 
	 * @param user
	 *            User to convert.
	 * @return User DTO.
	 */
	public static UserDto createDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setName(user.getName());

		return userDto;
	}
	
	/**
	 * Converts a user DTO into a user.
	 * 
	 * @param userDto
	 *            User Dto to convert.
	 * @return User.
	 */
	public static User createObject(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getId());
		user.setName(userDto.getName());

		return user;
	}
}
