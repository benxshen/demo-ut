package com.example.demo.spring.repository;

import com.example.demo.entity.User;

public interface UserRepository {

	User findById(Long id);
}
