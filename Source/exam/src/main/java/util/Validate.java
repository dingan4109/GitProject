package util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Validate {
    private static final String BORROW_CODE = "^MS-[\\d]{4}$";
    private static final String SERVICE_CODE = "";
    private static final String NUMBER = "^[0-9]+$";
    private static final String EMAIL = "^[\\w\\d]+(\\.[\\w\\d]+)?@[\\w\\d]+\\.[\\w\\d]+(\\.[\\w\\d]+)?$";
    private static final String ID_NUMBER = "^[0-9]{9}$";
    private static final String PHONE_NUMBER = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
    private static final String DATE = "^([0-9]{4}[-]((0[13-9]|1[012])[-](0[1-9]|[12][0-9]|30)|(0[13578]|1[02])" +
            "[-]31|02[-](0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-]02[-/]29)$";

    public Validate() {
    }

    public static boolean validateNumber(String input) {
        return Pattern.matches(NUMBER,input);
    }

    public static boolean validatePhoneNo(String input) {
        return Pattern.matches(PHONE_NUMBER,input);
    }

    public static boolean validateIdNo(String input) {
        return Pattern.matches(ID_NUMBER,input);
    }

    public static boolean validateEmail(String input) {
        return Pattern.matches(EMAIL,input);
    }

    public static boolean validateDate (String input){
        return Pattern.matches(DATE,input);
    };
    public static boolean validateBorrowCode (String input){
        return Pattern.matches(BORROW_CODE,input);
    };

    public static long dateCompare(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        //Period period = Period.between(localDate1,localDate2);
        long gap = ChronoUnit.DAYS.between(localDate1,localDate2);

        return gap;
    }

    public static int compare(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //dd/MM format require zero padding ex: 01/03 not 1/3 hence in this case: using d/M instead
        LocalDate localDate1 = LocalDate.parse(date1,formatter);
        LocalDate localDate2 = LocalDate.parse(date2,formatter);


        if(localDate1.compareTo(localDate2) > 0) {
            return 1;
        }else if(localDate1.compareTo(localDate2) <0) {
            return -1;
        }else {
            return 0;
        }
    }
}
