package com.upgrade.utilities;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonUtilities {

    public static BigDecimal generateRandomNumberFromRange(int min, int max) {
        return BigDecimal.valueOf(Math.random() * (max - min + 1) + min).setScale(0, RoundingMode.DOWN);
    }

    public static  String convertDOBFormat(String dateOfBirth) {
        DateTime dt = new DateTime(dateOfBirth, DateTimeZone.UTC);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyy");
        return fmt.print(dt);
    }
}
