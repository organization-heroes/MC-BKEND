package com.cts.mcbkend.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.mcbkend.userservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUserName(String userName);
	UserEntity findByUserNameAndPassword(String userName, String password);
}