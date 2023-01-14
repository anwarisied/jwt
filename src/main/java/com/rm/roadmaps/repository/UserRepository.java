package com.rm.roadmaps.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.roadmaps.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
