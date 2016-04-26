package re.weplay._04._10._01_pwd_validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator
{
    public boolean check(String password)
    {
        Pattern pattern = Pattern.compile(".*[a-z&&[A-Z]]+.*");
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }
}
