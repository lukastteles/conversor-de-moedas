package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.to.UserRequestTO;
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

    private final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    private UserRepository userRepository;

    /**
     * Default constructor
     * @param userRepository user repository object
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Save user object
     * @param userRequestTO {@link com.lukastteles.conversordemoedas.model.to.UserRequestTO} user request object
     * @return {@link com.lukastteles.conversordemoedas.model.entity.User}
     */
    public User save(UserRequestTO userRequestTO) {
        User user = new User(userRequestTO.getName());
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
