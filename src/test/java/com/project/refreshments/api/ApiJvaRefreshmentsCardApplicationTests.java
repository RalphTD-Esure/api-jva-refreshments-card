//package com.project.refreshments.api;

//class ApiJvaRefreshmentsCardApplicationTests {
//
//	@Mock
//	private OnboardUserService onboardUserService;
//
//    private static final OnboardUserRequest onboardUserRequest = new OnboardUserRequest();
//
//	private RefreshmentsCardController refreshmentsCardController;
//
//	@Before
//	public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        refreshmentsCardController = new RefreshmentsCardController(onboardUserService);
//    }
//
//    @Test
//    public void testOnboardUserSucceeds() {
//        OnboardUserResponse onboardUserResponse = refreshmentsCardController.onboardUser(onboardUserRequest);
//        verify(onboardUserService).saveUserToDatastore(onboardUserRequest);
//    }
//}
