package main.test;

import main.java.ejb.User;
import main.java.sessionbeans.Login;
import main.java.sessionbeans.RemoteLoginBean;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ejb.SupportEjb;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(CdiRunner.class)
@AdditionalClasses(RemoteLoginBean.class)
@SupportEjb
public class testLogin {

    //@Inject @Named("zz")
    @EJB
    Login login;

    @Test
    public void test_valid_user1(){
        User user = login.getFirstUser();
        if (user == null) {
            assert false;
        } else {
            assert login.checkValidUser(user.getUsername(), user.getPassword()).equals("itemList");
        }
    }

    @Test
    public void test_valid_user2(){
        User user = login.getFirstUser();
        if (user == null) {
            assert false;
        } else {
            assert login.checkValidUser(user.getUsername().toUpperCase(), user.getPassword()).equals("itemList");
        }
    }

    @Test
    public void test_valid_user3(){
        User user = login.getFirstUser();
        if (user == null) {
            assert false;
        } else {
            assert login.checkValidUser(user.getUsername().toLowerCase(), user.getPassword()).equals("itemList");
        }
    }

    @Test
    public void test_valid_user4(){
        assert login.checkValidUser("testUser", "testPassword").equals("failure");
    }

}
