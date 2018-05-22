package main.test;

import main.java.sessionbeans.Login;
import main.java.sessionbeans.LoginBean;
import main.java.sessionbeans.RemoteLoginBean;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;


@RunWith(CdiRunner.class)
@AdditionalClasses(RemoteLoginBean.class)
public class LoginTest {

    @Inject
    Login login;

    @Test
    public void test1() {
        String a = login.checkValidUser("a", "a");
        System.out.println(a);
    }
}