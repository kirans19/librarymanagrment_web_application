package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileNumberValidation {
	
	public static boolean isValidMobileNumber(String ValidMobileNumber)
    {
        String Constraints = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        Pattern pattern = Pattern.compile(Constraints);
        if (ValidMobileNumber == null) {
            return false;
        }
        Matcher m = pattern.matcher(ValidMobileNumber);
        return m.matches();
    }
}
