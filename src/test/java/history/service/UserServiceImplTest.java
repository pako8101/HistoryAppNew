package history.service;

import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.entity.UserRoleEnt;
import HistoryAppGradleSecurity.model.enums.UserRoleEnum;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.service.ApplicationUserDetailsService;
import HistoryAppGradleSecurity.service.impl.UserServiceImpl;
import HistoryAppGradleSecurity.session.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
  private   Consumer<Authentication>successfulLoginProcessor;
  @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private ApplicationUserDetailsService serviceToTest;
@Mock
    private UserRepository userRepositorytoMock;
    @Captor
    private ArgumentCaptor<UserEnt> userEntityCaptor;
@Mock
private UserServiceImpl toTest;
@Mock
    private PasswordEncoder mockPasswordEncoder;

private UserSubscribeBindingModel bindingModelToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new ApplicationUserDetailsService(
                userRepositorytoMock
        );
        toTest = new UserServiceImpl(
                userRepositorytoMock,
                mockPasswordEncoder,
                null,
                new LoggedUser(userRepositorytoMock)
                ,
                new ModelMapper()
               ,null,null
        );
    }


    @Test
    void testUserSubscribe() {
        // Arrange

        bindingModelToTest = new UserSubscribeBindingModel();
              bindingModelToTest
                        .setUsername("pako");
        bindingModelToTest  .setFullName("pako");
        bindingModelToTest   .setEmail("pako@softuni.bg");
        bindingModelToTest       .setPassword("pako");
        bindingModelToTest       .setConfirmPassword("pako");
        bindingModelToTest        .setAge(45);

        userDetailsService.loadUserByUsername(bindingModelToTest.getUsername());

        when(mockPasswordEncoder.encode(bindingModelToTest.getPassword()))
                .thenReturn(bindingModelToTest.getPassword()+bindingModelToTest.getPassword());

when(userDetailsService.loadUserByUsername(bindingModelToTest.getUsername()))
        .thenReturn(isNotNull());
        // ACT
        toTest.subscribeUser(bindingModelToTest,
                successfulLoginProcessor
        );

        // Assert
        verify(userRepositorytoMock).save(userEntityCaptor.capture());

        UserEnt actualSavedEntity = userEntityCaptor.getValue();

      Assertions.assertNotNull(userDetailsService.loadUserByUsername(bindingModelToTest.getUsername()));
        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(bindingModelToTest.getFullName(), actualSavedEntity.getFullName());
       Assertions.assertEquals(bindingModelToTest.getUsername(), actualSavedEntity.getUsername());
        Assertions.assertEquals(bindingModelToTest.getPassword() + bindingModelToTest.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(bindingModelToTest.getEmail(), actualSavedEntity.getEmail());
    }




    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pako")
        );
    }

    @Test
    void testUserFoundException() {
        // Arrange
        UserEnt testUserEntity = createTestUser();
        when(userRepositorytoMock.findUserEntByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        // Act
        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        // Assert
        assertNotNull(userDetails);
        assertEquals(
                testUserEntity.getUsername(),
                userDetails.getUsername(),
                "Username is not mapped.");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEnt createTestUser() {
        return new UserEnt()
                .setFullName("FullName")
                .setEmail("pako@softuni.bg")
                .setUsername("pako")
                .setPassword("pako")
                .setRoles(List.of(
                        new UserRoleEnt().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEnt().setRole(UserRoleEnum.USER)
                ));
    }
}
