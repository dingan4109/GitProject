package util;

public class Validation {
    public static boolean checkEmail(String email){
        String regEmail ="^[a-z]\\w+@gmail+\\.[a-z]+$";
        return email.matches(regEmail);
    }
}
