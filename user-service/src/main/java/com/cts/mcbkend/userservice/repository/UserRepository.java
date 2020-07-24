package com.cts.mcbkend.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.mcbkend.userservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByUserName(String userName);
	public UserEntity findByUserNameAndPassword(String userName, String password);
	public long deleteById(Long Id);
	public long countById(Long id);
}