package com.project.refreshments.api;

import com.project.refreshments.dto.AuthenticationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping({ "/hello" })
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/login")
    public String showLoginForm(WebRequest webRequest, Model model) {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        model.addAttribute("loginForm", authenticationRequestDto);
        log.debug(authenticationRequestDto.toString() + "HELLORALPH");
        return "login";
    }
}


//    @RequestMapping("/login")
//    public boolean login(@RequestBody AuthenticationRequestDto user) {
//        return
//                user.getUsername().equals("user") && user.getPassword().equals("password");
//    }
//
//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//                .decode(authToken)).split(":")[0];
//    }
//}

//
////    @PostMapping("/user/login")
////    public ResponseEntity<HttpStatus> login(@ModelAttribute("loginForm") @Valid AuthenticationRequestDto authenticationRequest, HttpServletRequest request, Errors errors) {
////        System.out.println(authenticationRequest.getPin() + "HELLO LOGIN");
////        return new ResponseEntity<>(HttpStatus.OK);
////    }
//
//    @GetMapping(value = "/user/loginFailed")
//    public String loginError(Model model) {
//        log.info("Login attempt failed");
//        model.addAttribute("error", "true");
//        return "login";
//    }
//
//    @PostMapping(value = "/login")
//    public ModelAndView user(@ModelAttribute("loginForm") @Valid AuthenticationRequestDto authenticationRequestDto, HttpServletRequest request, Errors errors){
//        return (new ModelAndView("homepage", "homepage", authenticationRequestDto));
//    }
//
//    @GetMapping(value = "/logout")
//    public String logout(SessionStatus session) {
//        SecurityContextHolder.getContext().setAuthentication(null);
//        session.setComplete();
//        return "redirect:/welcome";
//    }
////    @PostMapping(value = "/user/login")
////    public String postLogin(Model model, @ModelAttribute("loginForm") @Valid AuthenticationRequestDto authenticationRequest, HttpServletRequest request, Errors errors, HttpSession session) {
////        log.info("postLogin()");
////        // read principal out of security context and set it to session
////        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
////        validatePrinciple(authentication.getPrincipal());
////        UserEntity loggedInUser = ((CustomUserDetails) authentication.getPrincipal()).getClass();
////        model.addAttribute("currentUser", loggedInUser.getUsername());
////        session.setAttribute("userId", loggedInUser.getUserId());
////        return "redirect:/homepage";
////    }
//    private void validatePrinciple(Object principal) {
//        if (!(principal instanceof CustomUserDetails)) {
//            throw new  IllegalArgumentException("Principal can not be null!");
//        }



