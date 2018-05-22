package main.test;

import main.java.sessionbeans.Login;
import main.java.sessionbeans.RemoteLoginBean;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ejb.SupportEjb;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

@RunWith(CdiRunner.class)
@AdditionalClasses(RemoteLoginBean.class)
@SupportEjb
public class testLogin {

    //@Inject @Named("zz")
    @EJB
    Login login;

    @Test
    public void test_valid_user1(){
        assert login.checkValidUser("a", "a").equals("itemPage");
    }

    @Test
    public void test_valid_user2(){
        assert login.checkValidUser("A", "a").equals("itemPage");
    }

    @Test
    public void test_invalid_user1(){
        assert login.checkValidUser("notindb", "xxx").equals("failure");
    }

    @Test
    public void test_invalid_user2(){
        assert login.checkValidUser("a", "A").equals("failure");
    }


}
