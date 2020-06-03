//package com.project.refreshments.converter;
//
//import com.project.api.refreshments.swagger.model.RegistrationRequestDto;
//import com.project.refreshments.model.AuthenticatedUser;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class RegistrationRequestDtoToUserConverter implements Converter<AuthenticatedUser, RegistrationRequestDto> {
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
////    public User convert(final @NonNull RegistrationRequestDto request) {
////        return new User().setEmployeeId(request.getEmployeeId()).setFirstName(request.getFirstName())
////                .setLastName(request.getLastName()).setemail(request.getemail()).setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
////    }
//
//
////    public AuthenticatedUser convert(final @NonNull RegistrationRequestDto request) {
////        return new AuthenticatedUser().setEmployeeId(request.getEmployeeId()).setCardId(request.getCardId()).setFirstName(request.getFirstName())
////                .setLastName(request.getLastName()).setEmail(request.getemail()).setEncryptedPassword(request.getPassword());
////    }
//}
