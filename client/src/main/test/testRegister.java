package main.test;


import main.java.ejb.User;
import main.java.sessionbeans.Login;
import main.java.sessionbeans.Register;
import main.java.sessionbeans.RemoteLoginBean;
import main.java.sessionbeans.RemoteRegistrationBean;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ejb.SupportEjb;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(CdiRunner.class)
@AdditionalClasses(RemoteRegistrationBean.class)
@SupportEjb
public class testRegister {

    @EJB
    Register register;

    @Test
    public void existing_user_failure1(){
        User user = register.getFirstUser();
        if (user != null) {
            assert register.checkValidUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getName()).equals("failure");
        } else {
            assert false;
        }
    }

    @Test
    public void existing_user_failure2(){
        User user = register.getFirstUser();
        if (user != null) {
            assert register.checkValidUser(user.getUsername().toUpperCase(), user.getPassword(), user.getEmail(), user.getName()).equals("failure");
        } else {
            assert false;
        }
    }

    @Test
    public void adding_new_user(){
        assert register.checkValidUser("testUser", "Testuser", "testuser", "TestUser").equals("itemPage");

        register.deleteUser("testUser");
    }

}
