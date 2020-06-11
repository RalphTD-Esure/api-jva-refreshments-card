package com.project.refreshments.api;

import com.project.api.refreshments.swagger.model.AuthenticationResponse;
import com.project.api.refreshments.swagger.model.ErrorInfo;
import com.project.api.refreshments.swagger.model.RegistrationResponse;
import com.project.api.refreshments.swagger.model.RegistrationResponseDetail;
import com.project.refreshments.dto.AuthenticationRequestDto;
import com.project.refreshments.dto.RegistrationRequestDto;
import com.project.refreshments.dto.UserCheckDto;
import com.project.refreshments.exception.UserAlreadyExistsException;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.service.AccountService;
import com.project.refreshments.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticationController(final UserService userService, final AccountService accountService, AccountRepository accountRepository) {
        this.userService = userService;
    }

    @PostMapping(path = "/userCheck", consumes = "application/json")
    public ResponseEntity<String> userCheck(@RequestBody @Valid final UserCheckDto usernameDto) {
        return new ResponseEntity<>(userService.welcome(usernameDto.getCardId()), HttpStatus.OK);
    }

    @PostMapping(path = "/registration", consumes = "application/json")
    public ResponseEntity<RegistrationResponse> onboardUser(@RequestBody @Valid final RegistrationRequestDto registrationRequestDto) {
        List<ErrorInfo> errorInfos = new ArrayList<>();
        try {
            log.info("Adding user with credentials: {}", registrationRequestDto);
            this.userService.register(registrationRequestDto);
            return new ResponseEntity<>(new RegistrationResponse().addResultsItem(new RegistrationResponseDetail()
                    .successMessage("Successfully added " + registrationRequestDto.getFirstName() + " " + registrationRequestDto.getLastName() + " to the system.")), HttpStatus.ACCEPTED);
        } catch (UserAlreadyExistsException e) {
            log.info("A user with the Card ID " + registrationRequestDto.getCardId() + " already exists.");
            errorInfos.add(new ErrorInfo()
                    .message("A user with the Card ID " + registrationRequestDto.getCardId() + " already exists. Please check the information entered and try again."));
            return new ResponseEntity<>(new RegistrationResponse().addInfosItem(errorInfos.get(0)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid final AuthenticationRequestDto authenticationRequestDto) {
        List<ErrorInfo> errorInfos = new ArrayList<>();
        try {
            AuthenticatedUser authenticatedUser = userService.logIn(authenticationRequestDto);
            errorInfos.add(new ErrorInfo().message("JWT: " + authenticatedUser.getToken()));
            return new ResponseEntity<>(new AuthenticationResponse().addInfosItem(errorInfos.get(0)), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }
    }
}
