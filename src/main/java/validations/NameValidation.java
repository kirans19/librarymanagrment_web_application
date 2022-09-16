package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation {

	public static boolean isValidname(String ValidName)
    {
        String Constraints = "^[A-Za-z]+";
        Pattern pattern = Pattern.compile(Constraints);
        if (ValidName == null) {
            return false;
        }
        Matcher m = pattern.matcher(ValidName);
        return m.matches();
    }
}
