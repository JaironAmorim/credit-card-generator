package com.jaironamorim.creditcardgenerator.service.impl;

import com.jaironamorim.creditcardgenerator.model.dto.CreditCardDto;
import com.jaironamorim.creditcardgenerator.model.enums.LabelEnum;
import com.jaironamorim.creditcardgenerator.service.CreditCardGenarateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardGenarateServiceImpl implements CreditCardGenarateService {

    @Override
    public CreditCardDto generateCreditCard(String label, String checkdigit) {

        String number;
        LabelEnum labelEnum = LabelEnum.getLabel(label);
        List<String> prefixList = LabelEnum.getPrefixList(labelEnum);

        if(Objects.isNull(checkdigit)){
            number = generateCardNumber(prefixList);

        } else{
            do {
                number = generateCardNumber(prefixList);
            } while (!(number.substring(number.length()-1)).equals(checkdigit));

        }

        return  CreditCardDto.builder()
                .number(number)
                .cvv("321")
                .expires("05/28")
                .flag(labelEnum.getLabel())
                .owner("Jane Doe")
                .isValid(isValidCreditCardNumber(number))
                .build();

    }

    private String generateCardNumber(List<String> prefixList){

        int randomListIndex = (int) Math.floor(Math.random() * prefixList.size());
        String ccnumber = prefixList.get(randomListIndex);
        return generateCCNumber(ccnumber);

    }

    private String generateCCNumber(String prefix) {

        final int length = 16;
        StringBuilder ccnumber = new StringBuilder(prefix);

        while (ccnumber.length() < (length - 1)) {
            ccnumber.append(Double.valueOf(Math.floor(Math.random() * 10)).intValue());
        }

        String reversedCCnumberString = strrev(ccnumber.toString());

        List<Integer> reversedCCnumberList = new ArrayList<>();

        for (int i = 0; i < reversedCCnumberString.length(); i++) {
            reversedCCnumberList.add(Integer.valueOf(String.valueOf(reversedCCnumberString.charAt(i))));
        }

        int sum = 0;
        int pos = 0;

        while (pos < length - 1) {

            int odd = reversedCCnumberList.get(pos) * 2;
            if (odd > 9) {
                odd -= 9;
            }

            sum += odd;

            if (pos != (length - 2)) {
                sum += reversedCCnumberList.get(pos + 1);
            }

            pos += 2;

        }

        int checkdigit = Double.valueOf(((Math.floor(sum / 10) + 1) * 10 - sum) % 10).intValue();
        ccnumber.append(checkdigit);
        return ccnumber.toString();

    }

    private String strrev(String str) {

        if(Objects.isNull(str)) return "";

        StringBuilder revstr = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            revstr.append(str.charAt(i));
        }
        return revstr.toString();

    }


    public boolean isValidCreditCardNumber(String creditCardNumber) {
        boolean isValid = false;

        try {
            String reversedNumber = new StringBuffer(creditCardNumber).reverse().toString();
            int mod10Count = 0;
            for (int i = 0; i < reversedNumber.length(); i++) {
                int augend = Integer.parseInt(String.valueOf(reversedNumber.charAt(i)));
                if (((i + 1) % 2) == 0) {
                    String productString = String.valueOf(augend * 2);
                    augend = 0;
                    for (int j = 0; j < productString.length(); j++) {
                        augend += Integer.parseInt(String.valueOf(productString.charAt(j)));
                    }
                }

                mod10Count += augend;
            }

            if ((mod10Count % 10) == 0) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }

        return isValid;
    }

}
