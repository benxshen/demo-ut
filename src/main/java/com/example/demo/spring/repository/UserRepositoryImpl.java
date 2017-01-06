package com.example.demo.spring.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
//		return null;
		throw new RuntimeException("not implement!!!");
	}

}
