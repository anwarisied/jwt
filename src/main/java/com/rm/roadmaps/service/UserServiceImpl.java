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
        Optional<User> user = userRepository.findById(Id);
        return unwrapUser(user, Id);
    }

    @Override
    public User getUser(String email) {
        Optional<User> user=userRepository.findByEmail(email);
        return unwrapUser(user,-1L);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, User.class);
    }
}
