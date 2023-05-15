package com.ana.client.model;

import com.ana.client.model.impl.LoginModelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginModelTest {

    private static final String BASE_URL = "http://localhost:%d/login";

    private LoginModel loginModel;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${local.server.port}")
    private int port;

    @BeforeEach
    public void setup() throws Exception {
        final String URL = String.format(BASE_URL, port);
        loginModel = new LoginModelImpl(restTemplate.getRestTemplate(), URL);
    }

    @Test
    public void testValidateCredentialsValid() {
        // test valid credentials

        // a valid username matches this expression "^[a-zA-Z0-9_-]{6,30}$"
        // between 6 and 30 characters and contains only letters, numbers, underscores, and hyphens
        String username = "valid_username";

        // a valid password matches this expression "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$"
        // between 8 and 30 characters and contains at least one uppercase letter, one lowercase letter, and one digit
        String password = "ValidPassword1";

        boolean validation = loginModel.validateCredentials(username, password);

        assertTrue(validation);
    }

    @Test
    public void testValidateCredentialsInvalid() {
        // case 1: username is too short
        // case 2: username is too long
        // case 3: username contains invalid characters
        // case 4: password is too short
        // case 5: password is too long
        // case 6: password does not contain at least one uppercase letter
        // case 7: password does not contain at least one lowercase letter
        // case 8: password does not contain at least one digit

        // testing case 1: username is too short
        String username = "short";
        String password = "ValidPassword1";

        boolean validation = loginModel.validateCredentials(username, password);

        assertFalse(validation);

        // testing case 2: username is too long
        username = "this_username_is_too_long_to_be_valid";
        password = "ValidPassword1";

        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 3: username contains invalid characters
        username = "invalid_username!";
        password = "ValidPassword1";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 4: password is too short
        username = "valid_username";
        password = "short";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 5: password is too long
        username = "valid_username";
        password = "this_password_is_too_long_to_be_valid";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 6: password does not contain at least one uppercase letter
        username = "valid_username";
        password = "invalidpassword1";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 7: password does not contain at least one lowercase letter
        username = "valid_username";
        password = "INVALIDPASSWORD1";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);

        // testing case 8: password does not contain at least one digit
        username = "valid_username";
        password = "InvalidPassword";
        validation = loginModel.validateCredentials(username, password);
        assertFalse(validation);
    }

    @Test
    public void testLogin() {
        // username and password
        String username = "admin1";
        String password = "SiccAna2023";

        // act
        boolean result = loginModel.login(username, password);

        // assert
        assertTrue(result);
    }

    @Test
    public void testLoginAndValidateCredentials() {
        // set up the test
        final String USERNAME = "fjones";
        final String PASSWORD = "ValidPassw0rd";

        // perform the test
        boolean validation = loginModel.validateCredentials(USERNAME, PASSWORD);
        boolean result = loginModel.login(USERNAME, PASSWORD);

        // assert that the results are true as expected
        assertAll("TestLoginAndValidateCredentials",
                () -> assertTrue(validation),
                () -> assertTrue(result));
    }

}
