package cat.udl.tidic.amb.easytutor;

import org.junit.Assert;
import org.junit.Test;

public class TestUtils {

    @Test
    public void registrationValidEmail() throws Exception{
        Assert.assertTrue(LoginUtils.isValidEmailAddress("xxx@hotmail.com"));
    }

    @Test
    public void registrationInvalidEmail() throws Exception{
        Assert.assertFalse(LoginUtils.isValidEmailAddress("xxxhotmail.com"));
        Assert.assertFalse(LoginUtils.isValidEmailAddress("xxx@hotmailcom"));
        Assert.assertFalse(LoginUtils.isValidEmailAddress("xxxhotmailcom"));
    }

    @Test
    public void registrationValidPassword() throws Exception{
        Assert.assertTrue(LoginUtils.isValidPassword("hOlA12$$"));
    }

    @Test
    public void registrationInvalidPassword() throws Exception{
        Assert.assertFalse(LoginUtils.isValidPassword("hola"));
        Assert.assertFalse(LoginUtils.isValidPassword("holAA1"));
    }

    @Test
    public void registrationValidPhone() throws Exception{
        Assert.assertTrue(LoginUtils.isValidPhone("619441122"));
    }

    @Test
    public void registrationInvalidPhone() throws Exception{
        Assert.assertFalse(LoginUtils.isValidPhone("619"));
    }

    @Test
    public void loginAllFull() throws Exception{
        String username = "user1";
        String emptyUsername ="";
        String password = "MyPasSworD$$";
        String emptyPassword = "";
        Assert.assertTrue(LoginUtils.isAllLogin(username, password));
        Assert.assertFalse(LoginUtils.isAllLogin(emptyUsername, password));
        Assert.assertFalse(LoginUtils.isAllLogin(username, emptyPassword));
        Assert.assertFalse(LoginUtils.isAllLogin(emptyUsername, emptyPassword));
    }

}
