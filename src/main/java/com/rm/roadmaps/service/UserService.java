package com.rm.roadmaps.service;

import com.rm.roadmaps.entity.User;

public interface UserService {
    User getUser(Long Id);
    User getUser(String email);
    User saveUser(User user);

}
