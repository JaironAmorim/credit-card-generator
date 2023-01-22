package com.jaironamorim.creditcardgenerator.service;

import com.jaironamorim.creditcardgenerator.model.dto.CreditCardDto;

public interface CreditCardGenarateService {

    CreditCardDto generateCreditCard(String label, String checkdigit);

    boolean isValidCreditCardNumber(String creditCardNumber);

}
