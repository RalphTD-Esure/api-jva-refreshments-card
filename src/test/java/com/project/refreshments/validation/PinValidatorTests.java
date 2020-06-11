package com.project.refreshments.validation;

import com.project.refreshments.dto.RegistrationRequestDto;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PinValidatorTests {
    private static Validator validator;
    private static final Integer ONBOARD_EMPLOYEE_ID = 123456;
    private static final String ONBOARD_CARD_ID = "s7jTG7dqBy5wGO4L";
    private static final String ONBOARD_FIRST_NAME = "John";
    private static final String ONBOARD_LAST_NAME = "Smith";
    private static final String ONBOARD_EMAIL = "john@smith.com";
    private static final String ONBOARD_PIN = "1234";
    private static final String ONBOARD_MOBILE = "07123456789";

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidPin() {
        RegistrationRequestDto registrationRequestDto = createRegistrationRequest();

        Set<ConstraintViolation<RegistrationRequestDto>> constraintViolations = validator.validate(registrationRequestDto);

        assertThat(constraintViolations.isEmpty());
    }

    @Test
    public void testInvalidPin() {
        RegistrationRequestDto registrationRequestDto = createRegistrationRequest();
        registrationRequestDto.setConfirmPin("1111");

        Set<ConstraintViolation<RegistrationRequestDto>> constraintViolations = validator.validate(registrationRequestDto);

        assertEquals(1, constraintViolations.size());
    }

    private RegistrationRequestDto createRegistrationRequest()
    {
        RegistrationRequestDto registrationRequest = new RegistrationRequestDto();
        registrationRequest.setEmployeeId(ONBOARD_EMPLOYEE_ID);
        registrationRequest.setCardId(ONBOARD_CARD_ID);
        registrationRequest.setFirstName(ONBOARD_FIRST_NAME);
        registrationRequest.setLastName(ONBOARD_LAST_NAME);
        registrationRequest.setEmail(ONBOARD_EMAIL);
        registrationRequest.setPin(ONBOARD_PIN);
        registrationRequest.setConfirmPin(ONBOARD_PIN);
        registrationRequest.setMobile(ONBOARD_MOBILE);

        return registrationRequest;
    }
}
