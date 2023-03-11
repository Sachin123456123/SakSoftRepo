package com.saksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksoft.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
