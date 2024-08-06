package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.schedulers.EmailScheduler;
import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.view.UserViewModel;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.repository.UserRoleRepository;
import HistoryAppGradleSecurity.service.UserService;
import HistoryAppGradleSecurity.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final EmailScheduler emailScheduler;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService, LoggedUser loggedUser, ModelMapper modelMapper,
                           EmailScheduler emailScheduler, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.emailScheduler = emailScheduler;
        this.userRoleRepository = userRoleRepository;
    }



    @Override
    public void subscribeUser(UserSubscribeBindingModel userSubscribeBindingModel,
                              Consumer<Authentication> successfulLoginProcessor
    ) {

//        userRepository.save(map(userSubscribeBindingModel));
//    }
//
//    private UserEnt map(UserSubscribeBindingModel userSubscribeBindingModel) {
//        UserEnt mappedEntity = modelMapper.map(userSubscribeBindingModel, UserEnt.class);
//
//        mappedEntity.setPassword(passwordEncoder.encode(userSubscribeBindingModel.getPassword()));
//
//        return mappedEntity;
//    }

        UserEnt userEntity = modelMapper.map(userSubscribeBindingModel, UserEnt.class);
        userEntity.setPassword(passwordEncoder.encode(userSubscribeBindingModel.getPassword()));
//                new UserEnt().
//
//                setFullName(userSubscribeBindingModel.getFullName()).
////               setAge(userSubscribeBindingModel.getAge()).
//                setEmail(userSubscribeBindingModel.getEmail()).
//                setUsername(userSubscribeBindingModel.getUsername()).
//                setPassword(passwordEncoder.encode(userSubscribeBindingModel.getPassword())
//                );

        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userSubscribeBindingModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);

        emailScheduler.sendSubscriptionEmails();
    }

    @Override
    public UserEnt findCurrentUserLoginEntity() {
        return userRepository
                .findUserEntByUsername(loggedUser.getUsername())
                .orElse(null);
    }

    @Override
    public UserViewModel getUserProfile() {
    UserEnt user = loggedUser.get();
        return modelMapper.map(user,UserViewModel.class);
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findUserEntByUsername(username).isPresent();
    }

//    @Override
//    public void registerAndLoginUser(UserServiceModel userServiceModel) {
//        UserEnt newUser = modelMapper.map(userServiceModel, UserEnt.class);
//        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
//
//        UserRoleEnt userRole = userRoleRepository.
//                findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow(
//                        () -> new IllegalStateException("USER role not found. Please seed the roles."));
//
//        newUser.addRole(userRole);
//
//        newUser = userRepository.save(newUser);
//
//        UserDetails principal = userDetailsService.loadUserByUsername(newUser.getUsername());
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                principal,
//                newUser.getPassword(),
//                principal.getAuthorities()
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

    @Override
    public UserEnt findByName(String username) {

        return userRepository.findUserEntByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public UserEnt findUserByMail(String email) {
        return userRepository.findByEmail(email)
                .map(userEnt -> modelMapper.map(userEnt, UserEnt.class))
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public UserViewModel findBId(Long id) {
        return userRepository.findById(id).
                map(user -> modelMapper.map(user,UserViewModel.class))
                .orElse(null);
    }

}


