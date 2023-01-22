package com.jaironamorim.creditcardgenerator.controller;

import com.jaironamorim.creditcardgenerator.model.dto.CreditCardDto;
import com.jaironamorim.creditcardgenerator.service.CreditCardGenarateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/credit-card")
public class CreditCardGeneratorController {

    private final CreditCardGenarateService creditCardGenarateService;

    @GetMapping("/generate/{label}")
    public ResponseEntity<CreditCardDto> generateCreditCard( @PathVariable(value = "label") String label,
                                                             @RequestParam(value = "checkdigit", required = false)
                                                             String checkdigit) {

        CreditCardDto creditCardDto = creditCardGenarateService.generateCreditCard(label, checkdigit);

        return ResponseEntity.ok().body(creditCardDto);

    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateCard(@RequestParam(value = "numberOfCard") String numberOfCard){

        Boolean isvalid = creditCardGenarateService.isValidCreditCardNumber(numberOfCard);

        return ResponseEntity.ok().body(isvalid);

    }

}
