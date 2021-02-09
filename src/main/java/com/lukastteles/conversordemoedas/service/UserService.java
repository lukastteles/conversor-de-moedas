package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for management of User request object and to access repository
 * @author Lukas Teles
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    private UserRepository userRepository;

    /**
     * Default constructor
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Save user object
     * @param name {@link java.lang.String} user name
     * @return {@link com.lukastteles.conversordemoedas.model.entity.User}
     */
    public User save(String name) {
        User user = new User(name);
        logger.info(String.format("User object to save: %s", user));
        return this.userRepository.save(user);
    }

    /**
     * Get all users
     * @return {@link java.util.List}
     */
    public List<User> getAll() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }
}
