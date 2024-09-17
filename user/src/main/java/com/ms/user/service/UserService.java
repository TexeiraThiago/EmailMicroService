package com.ms.user.service;

import com.ms.user.domain.models.UserModel;
import com.ms.user.domain.repository.UserRepository;
import com.ms.user.producers.UserProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducer.publishedMessageEmail(userModel);
        return userModel ;
    }
}
