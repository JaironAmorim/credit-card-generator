package com.jaironamorim.creditcardgenerator.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCardDto {
    private String number;
    private String cvv;
    private String expires;
    private String flag;
    private String owner;
    private Boolean isValid;
}
