package com.project.refreshments.api;

import com.project.api.refreshments.swagger.model.ErrorInfo;
import com.project.api.refreshments.swagger.model.PurchaseResponse;
import com.project.api.refreshments.swagger.model.TopUpResponse;
import com.project.api.refreshments.swagger.model.TopUpResponseDetail;
import com.project.refreshments.dto.PurchaseRequestDto;
import com.project.refreshments.dto.TopUpRequestDto;
import com.project.refreshments.service.PurchaseService;
import com.project.refreshments.service.TopUpService;
import com.project.refreshments.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class RefreshmentsCardController {

    private final UserService userService;
    private final TopUpService addFundsService;
    private final PurchaseService purchaseService;

    public RefreshmentsCardController(UserService userService, TopUpService addFundsService,
                                      PurchaseService purchaseService) {
        this.userService = userService;
        this.addFundsService = addFundsService;
        this.purchaseService = purchaseService;
    }


    @PostMapping(path = "/topUp", consumes = "application/json")
    public ResponseEntity<TopUpResponse> addFunds(@RequestBody TopUpRequestDto topUpRequestDto) {
        List<ErrorInfo> errorInfos = new ArrayList<>();
        BigDecimal newBalance;
        try {
            newBalance = addFundsService.addFunds(topUpRequestDto);
            return new ResponseEntity<>(new TopUpResponse().addResultsItem(
                    new TopUpResponseDetail().successMessage("Successfully added £" + topUpRequestDto.getAmount() + " to your account. Your new balance is: £" + newBalance)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            errorInfos.add(new ErrorInfo().message("Username " + topUpRequestDto.getUsername() + " not found."));
            return new ResponseEntity<>(new TopUpResponse().addInfosItem(errorInfos.get(0)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/purchase", consumes = "application/json")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        List<ErrorInfo> errorInfos = new ArrayList<>();
        BigDecimal newBalance;
        newBalance = purchaseService.makePurchase(purchaseRequestDto);
        return new ResponseEntity<PurchaseResponse>(new PurchaseResponse().successMessage("Thank you for your purchase, your new balance is £" + newBalance), HttpStatus.OK);
    }
}


