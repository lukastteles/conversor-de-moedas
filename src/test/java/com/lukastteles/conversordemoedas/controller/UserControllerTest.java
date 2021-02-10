package com.lukastteles.conversordemoedas.controller;

import com.lukastteles.conversordemoedas.model.TO.UserRequestTO;
import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    public UserRepository userRepository;

    @Test
    public void postSaveTestShouldReturnStatusCode200(){
        BDDMockito.when(userRepository.save(new User("test"))).thenReturn(new User(1L, "test"));
        //Test with 200 OK
        ResponseEntity<String> response = testRestTemplate.postForEntity("/users", new UserRequestTO("test"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postSaveTestShouldReturnStatusCode400(){
        //Test with 400 BAD_REQUEST - null name
        ResponseEntity<String> response = testRestTemplate.postForEntity("/users", new UserRequestTO(null), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        //Test with 400 BAD_REQUEST - empty name
        ResponseEntity<String> response2 = testRestTemplate.postForEntity("/users", new UserRequestTO(""), String.class);
        Assertions.assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getAllUsersTestShouldReturnStatusCode200(){
        //Test with 200 OK
        ResponseEntity<String> response = testRestTemplate.getForEntity("/users", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
