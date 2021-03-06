package cat.udl.tidic.amb.easytutor;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUtils {

    /**
     * This method checks if the provided string represents a valid email address and returns true
     * if it is.
     *
     * @param email email address
     * @return boolean
     */

    public static boolean isValidEmailAddress(String email){
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /**
     * This method check if the provided string represents a valid password and returns true if it
     * is. A valid password must have a minimu lenght of 8 characters and contain at least:
     * 1 Uppercase
     * 1 symbol in this set @#$%^&+=!
     * 1 number
     * @param password password
     * @return boolean
     */

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * This method check if the provided string represents a valid phone and returns true if it
     * is. A valid phone must have a minimum lenght of 6 and maximum of 13, just numbers
     * @param phone phone number
     * @return boolean
     */

    public static boolean isValidPhone(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 6 || phone.length() > 13) {
                check = false;

            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

    /**
     * This method check if all login EditTexts (username and password) aren't empty.
     * @param username user name
     * @param password password
     * @return boolean
     */

    public static boolean isAllLogin(String username, String password){
        if (username.equals("") || password.equals("")){
            return false;
        }
        return true;
    }

    //TODO: getUserName as the part before @, make the test.

    public static String encodeLogin(String username, String password){
        String header = username + ":" + password;
        byte[] data = header.getBytes(StandardCharsets.UTF_8);
        header = Base64.encodeToString(data, Base64.DEFAULT);
        header = ("Authentication " + header).trim();
        return header;
    }

}
