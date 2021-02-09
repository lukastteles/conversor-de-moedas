package com.lukastteles.conversordemoedas.controller;

import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User endpoint controller
 * @author Lukas Teles
 */
@RestController
@RequestMapping("users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Default constructor
     * @param userService {@link com.lukastteles.conversordemoedas.service.UserService} service object
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to add user
     * @param name {@link java.lang.String} user name request object
     * @return {@link org.springframework.http.ResponseEntity}
     */
    @PostMapping
    @Transactional
    public ResponseEntity<User> save(
            @NotEmpty(message = "name is mandatory")
            @NotNull(message = "name is mandatory")
            @RequestBody String name) {
        logger.info(String.format(
                "starting save user request with name: %s", name));
        User savedUser = userService.save(name);

        logger.info(String.format("return save user request object: %s", savedUser.toString()));
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    /**
     * Endpoint to get all users
     * @return {@link org.springframework.http.ResponseEntity}
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers( ) {
        logger.info("starting request for all users");

        List<User> userList = userService.getAll();

        logger.info(String.format("return request for all users list: %s", userList.toString()));
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
