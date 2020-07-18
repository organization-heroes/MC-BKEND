package com.cts.mcbkend.userservice.service;

import java.util.List;

import com.cts.mcbkend.userservice.model.UserDto;

public interface UserService {
	public UserDto findByUserName(String userName);
	public UserDto findByUserNameAndPassword(String userName, String password);
	public List<UserDto> findAllUsers();
	public UserDto registerUser(UserDto userDto) throws Exception;
}