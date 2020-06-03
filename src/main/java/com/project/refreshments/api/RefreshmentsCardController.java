package com.project.refreshments.api;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.project.api.refreshments.swagger.model.TopUpDetail;
import com.project.api.refreshments.swagger.model.TopUpRequest;
import com.project.api.refreshments.swagger.model.TopUpResponse;
import com.project.refreshments.dto.RegistrationRequestDto;
import com.project.refreshments.exception.UserAlreadyExistsException;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.model.AuthenticationRequest;
import com.project.refreshments.service.TopUpService;
import com.project.refreshments.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@Slf4j
@Api(value = "Refreshments Card")
public class RefreshmentsCardController {

    private final UserService userService;
    private final TopUpService addFundsService;

    public RefreshmentsCardController(final UserService registrationService,
            TopUpService topUpService) {
        this.userService = registrationService;
        this.addFundsService = topUpService;
    }

//    @PostMapping("/user/registration")
//    public ResponseEntity<RegistrationResponse> registration(@RequestBody @Valid final RegistrationRequestDto registrationRequest, HttpServletRequest request, Errors errors
//    ) {
//        List<ErrorInfo> errorInfos = new ArrayList<>();
//        try {
//            log.info("Adding user with credentials: {}", registrationRequest);
//            userService.registration(registrationRequest);
//        } catch (UserAlreadyExistsException e) {
//            log.info("A user with the EmployeeId " + registrationRequest.getEmployeeId() + " already exists.");
//            errorInfos.add(new ErrorInfo()
//                    .message("A user with the EmployeeId " + registrationRequest.getEmployeeId() + " already exists. Please check the information entered and try again."));
//            return new ResponseEntity<>(new RegistrationResponse().addInfosItem(errorInfos.get(0)), HttpStatus.BAD_REQUEST);
//        }
//        ModelAndView mav = createModelAndView(registrationRequest, request, errors);
//        return new ResponseEntity<>(new RegistrationResponse().addResultsItem(new RegistrationResponseDetail()
//                .successMessage("Successfully added " + registrationRequest.getFirstName() + " " + registrationRequest.getLastName() + " to the system.")), HttpStatus.ACCEPTED);
//    }

    @PostMapping("/user/registration")
    public ModelAndView createModelAndView(@ModelAttribute("user") @Valid RegistrationRequestDto registrationRequestDto, HttpServletRequest request, Errors errors)
    {
        log.debug("Adding user with credentials: {}", registrationRequestDto);
        try {
            final AuthenticatedUser authenticatedUser = userService.register(registrationRequestDto);
        } catch (final UserAlreadyExistsException e) {
            log.info("A user with the EmployeeId " + registrationRequestDto.getEmployeeId() + " already exists.");
            ModelAndView modelAndView = new ModelAndView("registration", "user", registrationRequestDto);
            modelAndView.addObject("message", "An account for the Employee ID " + registrationRequestDto.getEmployeeId() + " already exists.");
            return modelAndView;
        }
        return new ModelAndView("registrationSuccess", "user", registrationRequestDto);
    }

//    "An account with the employee ID " + registrationRequestDto.getEmployeeId() + "already exists."

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest webRequest, Model model) {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
        model.addAttribute("user", registrationRequestDto);
        log.debug(registrationRequestDto.toString() + "HELLORALPH");
        return "registration";
    }


    @PostMapping("/user/performLogin")
    public ResponseEntity<AuthenticatedUser>
    signIn(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest.getPin());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/top-up")
    public ResponseEntity<TopUpResponse> addFunds(@RequestBody TopUpRequest topUpRequest) {

        BigDecimal newBalance = addFundsService.addFunds(topUpRequest);

        return new ResponseEntity<>(new TopUpResponse().addResultsItem(new TopUpDetail().successMessage("Successfully added " + topUpRequest.getTopUpAmount() + "to your account. Your new balance is: " + newBalance)), HttpStatus.OK);
    }

}



