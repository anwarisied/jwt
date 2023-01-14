package com.rm.roadmaps.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rm.roadmaps.entity.User;
import com.rm.roadmaps.exception.EntityNotFoundException;
import com.rm.roadmaps.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long Id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, User.class);
    }
}
