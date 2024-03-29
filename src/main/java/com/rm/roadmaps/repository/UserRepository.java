package com.rm.roadmaps.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.roadmaps.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
