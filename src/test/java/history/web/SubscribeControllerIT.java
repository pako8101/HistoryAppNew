package history.web;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SubscribeControllerIT.class)
//@ContextConfiguration
@AutoConfigureMockMvc
public class SubscribeControllerIT {

    @Autowired
    private MockMvc mvc;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public SubscribeControllerIT(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
void testSubscribe() throws Exception {

        mvc.perform(post("/users/subscribe")
                .param("username","pako")
                .param("password","pako")
                .param("confirmPassword","pako")
                .param("email","pako@gmail.com")
                .param("fullName","Pako")
                .param("gender","male")
                .param("age","34")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        Optional<UserEnt> userOpt =
        userRepository.findByEmail("pako@gmail.com");

        Assertions.assertTrue(userOpt.isPresent());

        UserEnt user = userOpt.get();
Assertions.assertEquals("pako", user.getPassword());
Assertions.assertEquals("Pako", user.getFullName());

Assertions.assertTrue(passwordEncoder.
        matches("pako", user.getPassword()));



    }
}
