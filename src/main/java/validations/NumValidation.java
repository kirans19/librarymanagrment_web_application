package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumValidation {
	
	public static boolean isValidnum(String ValidNum)
    {
        String Constraints = "^[0-9]+";
        Pattern pattern = Pattern.compile(Constraints);
        if (ValidNum == null) {
            return false;
        }
        Matcher m = pattern.matcher(ValidNum);
        return m.matches();
    }

}
