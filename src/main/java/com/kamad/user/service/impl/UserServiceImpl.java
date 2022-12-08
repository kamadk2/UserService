package com.kamad.user.service.impl;

import com.kamad.user.service.entities.Rating;
import com.kamad.user.service.entities.User;
import com.kamad.user.service.exceptions.ResourceNotFoundException;
import com.kamad.user.service.repositories.UserRepository;
import com.kamad.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with given ID : " + userId));
//        fetch rating of the above user from the RATING-SERVICE
//        http://localhost:8083/ratings/users/7cfb4416-32cc-460f-970c-9315fd31d9a6

        ArrayList<Rating> ratingsOfUser =restTemplate.getForObject("http://localhost:8083/ratings/users/"+ user.getId(), ArrayList.class);
        logger.info("{}",ratingsOfUser);
        user.setRatings(ratingsOfUser);
        return user;
    }
}


