package com.socialweb.socialweb;

import com.socialweb.socialweb.shared.GenericResponse;
import com.socialweb.socialweb.user.User;
import com.socialweb.socialweb.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    public static final String API_1_0_USERS = "/api/1.0/users";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @Before
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void postUser_whenUserIsValid_receiveOk(){
        User user = createValidUser();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private User createValidUser() {
        User user = new User();
        user.setUserName("testUser");
        user.setDisplayName("testDisplay");
        user.setPassword("Password");
        return user;
    }

    @Test
    public void postUser_whenUserIsValid_userSavedToDatabase(){
        User user = createValidUser();
        testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        assertEquals(userRepository.count(), 1);
    }

    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage(){
        User user = createValidUser();
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertNotNull(response.getBody().getMessage());
    }

    @Test
    public void postUser_whenUserIsValid_passwordIsHasheedInDatabase(){
        User user = createValidUser();
        testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        List<User> users = userRepository.findAll();
        User inDB = users.get(0);
        assertNotEquals(inDB.getPassword(), user.getPassword());

    }

}
