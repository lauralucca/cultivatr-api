package com.cultivatr.api.integration;

import com.cultivatr.api.PostgresConnection;
import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserSignUpIntegrationTest {
    @Value("${db.host}")
    String host;
    @Value("${db.port}")
    String port;
    @Value("${db.name}")
    String db_name;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;
    @Value("${db.driver}")
    String driver;

    User expectedUser;
    String name;
    String email;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserSignUpService userSignUpService;

    @BeforeAll
    void initializeTestData() {
        this.name = "Lucas";
        this.email = "lucas@cultivatr.com" + Math.random();
    }

    @Test
    void createsUser() throws Exception {

        String userAsJson = "{ \"name\": \"" + this.name + "\", " +
                "\"email\": \"" + this.email + "\"" + "}";

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isCreated());

        User expectedUser = userSignUpService.getUserByEmail(this.email);

        assertThat(expectedUser.getName()).isEqualTo(this.name);
        assertThat(expectedUser.getEmail()).isEqualTo(this.email);

    }

    @AfterAll
    void clearDataBase() throws Exception {

        PostgresConnection DBConnection = new PostgresConnection(host, port, db_name, username, password, driver);
        DBConnection.executeCommand("DELETE from user_account");
    }
}
