package com.project.refreshments.api;

import com.project.api.refreshments.swagger.model.ErrorInfo;
import com.project.api.refreshments.swagger.model.OnboardUserRequest;
import com.project.api.refreshments.swagger.model.OnboardUserResponse;
import com.project.refreshments.service.OnboardUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Refreshments Card")
public class RefreshmentsCardController {

    private final OnboardUserService onboardUserService;

    public RefreshmentsCardController(final OnboardUserService onboardUserService) {
        this.onboardUserService = onboardUserService;
    }

    @PostMapping("/user")
    public OnboardUserResponse onboardUser(@RequestBody OnboardUserRequest onboardUserRequest) {
        onboardUserService.saveUserToDatastore(onboardUserRequest);
        return new OnboardUserResponse().addInfosItem(new ErrorInfo().code("ACCEPTED").message("Successfully added new user to database"));
    }
}

//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
//    @PostMapping("/users")
//    void addUser(@RequestBody User user) {
//        userRepository.save(user);
//
//    }



