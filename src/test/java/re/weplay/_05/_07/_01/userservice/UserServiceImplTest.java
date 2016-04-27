package re.weplay._05._07._01.userservice;

import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest
{
    @Test
    public void assignPassword_set_an_encrypted_password_to_the_passed_user_and_updates_it()
    {
        //Arrange
        UserDAO userDAO = mock(UserDAO.class); //test spy
        SecurityService securityService = mock(SecurityService.class); //stub
        User user = mock(User.class); //stub

        UserServiceImpl userService = new UserServiceImpl(userDAO, securityService);

        String passwordMd5 = "f85y7nlMa4";
        String monPWD = "monPWD";
        when(user.getPassword()).thenReturn(monPWD);
        when(securityService.md5(monPWD)).thenReturn(passwordMd5);

        //Act
        try
        {
            userService.assignPassword(user);
        }
        catch(Exception e)
        {
            fail();
            e.printStackTrace();
        }

        //Assert
        verify(user).setPassword(passwordMd5);
        verify(userDAO).updateUser(user);
    }
}
