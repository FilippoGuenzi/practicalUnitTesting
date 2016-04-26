package re.weplay._04._10._01_pwd_validator;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PasswordValidatorTest
{
    @Test
    public void should_return_false_because_password_has_no_upper_case_letter()
    {
        //Arrange
        String password = "qdg";
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean expectedResult = false;
        //Act
        boolean test = passwordValidator.check(password);
        //Assert
        assertEquals("\nThe expected result of password check should be " + expectedResult + " because the password doesn't contain any UPPERCASE letter", expectedResult, test);
    }

    @Test public void should_return_false_because_password_has_no_lower_case_letters()
    {
        //Arrange
        String password = "QDKHG";
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean expectedResult = false;
        //Act
        boolean test = passwordValidator.check(password);
        //Assert
        assertEquals("\nThe expected result of password check should be " + expectedResult + " because the password doesn't contain any lowercase letter", expectedResult, test);
    }

    @Test public void should_return_true_because_password_provided_has_both_lower_case_and_upper_case()
    {
        //Arrange
        String password = "aQfYh";
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean expectedResult = true;
        //Act
        boolean test = passwordValidator.check(password);
        //Assert
        assertEquals("\nThe expected result of password check should be " + expectedResult + " because the password doesn't contain any special character", expectedResult, test);
    }
}
