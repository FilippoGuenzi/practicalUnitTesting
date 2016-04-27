package re.weplay._05._07._01.userservice;

public class UserServiceImpl
{
    private UserDAO userDAO;
    private SecurityService securityService;

    public void assignPassword(User user) throws Exception
    {
        String password = user.getPassword();
        String passwordMd5 = securityService.md5(password);
        user.setPassword(passwordMd5);
        userDAO.updateUser(user);
    }

    public UserServiceImpl(UserDAO dao, SecurityService security)
    {
        this.userDAO = dao;
        this.securityService = security;
    }
}
