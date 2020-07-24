package com.cts.mcbkend.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mcbkend.userservice.entity.UserEntity;
import com.cts.mcbkend.userservice.model.UserDto;
import com.cts.mcbkend.userservice.repository.UserRepository;
import com.cts.mcbkend.userservice.service.UserService;
import com.sun.el.parser.ParseException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public UserDto findByUserName(String userName) {
		UserDto userDto = null;
		UserEntity userEntity = userRepository.findByUserName(userName);
		if(userEntity!=null) {
			userDto = convertToDto(userEntity);
		}
		return userDto;
	}

	@Override
	public UserDto findByUserNameAndPassword(String userName, String password) {
		UserDto userDto = null;
		UserEntity userEntity = userRepository.findByUserNameAndPassword(userName, password);
		if(userEntity!=null) {
			userDto = convertToDto(userEntity);
		}
		return userDto;
	}
	
	@Override
	public List<UserDto> findAllUsers() {
		List<UserDto> userDtoList = null;
		List<UserEntity> userEntityList = userRepository.findAll();
		if(userEntityList!=null) {
			userDtoList = userEntityList.stream()
		      .map(this::convertToDto)
		      .collect(Collectors.toList());
		}
		return userDtoList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public UserDto registerUser(UserDto userDto) throws Exception{
		UserDto registerUserDto = null;
		UserEntity registerUserEntity = null;
		if(userDto!=null) {
			UserEntity userEntity = convertToEntity(userDto);
			registerUserEntity = userRepository.saveAndFlush(userEntity);
			if(registerUserEntity!=null) {
				registerUserDto = convertToDto(registerUserEntity);
			}
		}
		return registerUserDto;
	}
	
	
	private UserDto convertToDto(UserEntity userEntity) {
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
	    return userDto;
	}
	
	private UserEntity convertToEntity(UserDto userDto) throws ParseException {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
	    return userEntity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public String deleteByUserId(Long userId, UserDto userDto) throws Exception {
		long count = 0;
		long updateStatus = 0;
		String status = null;
		count = userRepository.countById(userId);
		if(count == 0) {
			status = "User is not valid";
		}else if(count > 0) {
			updateStatus = userRepository.deleteById(userId);
			if(updateStatus>0) {
				status = "User deleted successfully of user id "+userDto.getId();
			} else  {
				status = "Document deleted is failed for user id "+userDto.getId();
			}
		}
		return status;
	}
	
}