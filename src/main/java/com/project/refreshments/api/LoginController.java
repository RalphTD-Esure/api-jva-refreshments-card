package com.project.refreshments.api;

import javax.servlet.http.HttpSession;

import com.project.refreshments.dto.AuthenticationRequestDto;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.model.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@SessionAttributes({"currentUser"})
@Controller
public class LoginController
{
    @GetMapping("/user/login")
    public String showRegistrationForm(WebRequest webRequest, Model model) {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        model.addAttribute("login", authenticationRequestDto);
        log.debug(authenticationRequestDto.toString() + "HELLORALPH");
        return "registration";
    }

    @GetMapping(value = "/user/loginFailed")
    public String loginError(Model model) {
        log.info("Login attempt failed");
        model.addAttribute("error", "true");
        return "login";
    }
    @GetMapping(value = "/logout")
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/welcome";
    }
    @PostMapping(value = "/user/postLogin")
    public String postLogin(Model model, HttpSession session) {
        log.info("postLogin()");
        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        UserEntity loggedInUser = ((CustomUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUser", loggedInUser.getUsername());
        session.setAttribute("userId", loggedInUser.getUserId());
        return "redirect:/login";
    }
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof CustomUserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}

