package com.upgrade.utilities;

import com.github.javafaker.Faker;
import com.upgrade.pojos.loan.Borrower;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.upgrade.utilities.CommonUtilities.*;

public class CreateTestData {

    public static Borrower getRandomTestBorrower(String loanPurpose) {
        Borrower borrower = new Borrower();
        Faker faker = new Faker(new Locale("en-US"));

        borrower.setFirstName(faker.name().firstName());
        borrower.setLastName(faker.name().lastName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        borrower.setDob(simpleDateFormat.format(faker.date().between(Date.valueOf("1930-01-01"), Date.valueOf("2000-01-01"))));
        borrower.setCity(faker.address().city());
        borrower.setEmail(String.format("coding.%s@upgrade-challenge.com", generateRandomNumberFromRange(15000000, 20000000)));
        borrower.setPassword("System@987");
        borrower.setZipCode(faker.address().zipCode());
        borrower.setStreet(faker.address().streetAddress());
        borrower.setState("CA");
        borrower.setLoanPurpose(loanPurpose);
        borrower.setYearlyIncome(generateRandomNumberFromRange(150000, 200000));
        borrower.setAdditionalIncome(generateRandomNumberFromRange(5000, 10000));
        borrower.setDesiredLoanAmount(generateRandomNumberFromRange(5000, 10000));
        return borrower;
    }
}
