package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.entity.UserRoleEnt;
import HistoryAppGradleSecurity.model.enums.UserRoleEnum;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;


    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("pako") String defaultPassword) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


        this.defaultPassword = defaultPassword;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var moderatorRole = new UserRoleEnt().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEnt().setRole(UserRoleEnum.ADMIN);
            var userRole = new UserRoleEnt().setRole(UserRoleEnum.USER);

            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initNormalUser();
        }
    }

    private void initAdmin(){
        var adminUser = new UserEnt().
                setEmail("admin@example.com").
                setFullName("Admin").
                setUsername("admin").
                setPassword(passwordEncoder.encode(defaultPassword)).
                setRoles(userRoleRepository.findAll());

        userRepository.save(adminUser);
    }

    private void initModerator(){

        var moderatorRole = userRoleRepository.
                findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();

        var moderatorUser = new UserEnt().
                setEmail("moderator@example.com").
                setFullName("Moderator").
                setUsername("moderator").
                setPassword(passwordEncoder.encode(defaultPassword)).
                setRoles(List.of(moderatorRole));

        userRepository.save(moderatorUser);
    }

    private void initNormalUser(){

        var normalUser = new UserEnt().
                setEmail("user@example.com").
                setFullName("User").
                setUsername("user").
                setPassword(passwordEncoder.encode(defaultPassword));

        userRepository.save(normalUser);
    }
}
