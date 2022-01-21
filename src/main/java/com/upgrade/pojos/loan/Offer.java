package com.upgrade.pojos.loan;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Offer {

    @Setter @Getter String loanAmount;
    @Setter @Getter String monthlyPayment;
    @Setter @Getter String loanTerm;
    @Setter @Getter String interestRate;
    @Setter @Getter String apr;

}

