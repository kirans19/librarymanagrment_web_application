package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidation {

	public static boolean isValidMail(String ValidMail)
    {
		String Constraints="[_a-z0-9]+(\\.[a-z0-9]*)*@[a-z0-9]+\\.[a-z0-9]+(\\.[a-z0-9]*)*";
        //String Constraints = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(Constraints);
        if (ValidMail == null) {
            return false;
        }
        Matcher m = pattern.matcher(ValidMail);
        return m.matches();
    }
}
