package com.cultivatr.api.integration;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserSignUpIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserSignUpService userSignUpService;

    @Test
    void createsUser() throws Exception {
        String name = "Lucas";
        String email = "lucas@cultivatr.com" + Math.random();
        String userAsJson = "{ \"name\": \"" + name + "\", " +
                "\"email\": \"" + email + "\"" + "}";

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAsJson))
                .andExpect(status().isCreated());

        User expectedUser = userSignUpService.getUserByEmail(email);

        assertThat(expectedUser.getName()).isEqualTo(name);
        assertThat(expectedUser.getEmail()).isEqualTo(email);

    }
}
