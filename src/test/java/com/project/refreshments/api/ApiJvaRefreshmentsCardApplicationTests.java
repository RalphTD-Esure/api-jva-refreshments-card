//package com.project.refreshments.api;

//class ApiJvaRefreshmentsCardApplicationTests {
//
//	@Mock
//	private RegistrationService registrationService;
//
//    private static final RegistrationRequestDto registrationRequest = new RegistrationRequestDto();
//
//	private RefreshmentsCardController refreshmentsCardController;
//
//	@Before
//	public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        refreshmentsCardController = new RefreshmentsCardController(registrationService);
//    }
//
//    @Test
//    public void testRegistrationSucceeds() {
//        RegistrationResponse registrationResponse = refreshmentsCardController.registration(registrationRequest);
//        verify(registrationService).saveUserToDatastore(registrationRequest);
//    }
//}
