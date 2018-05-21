package test.java;

import main.java.sessionbeans.RegistrationBean;
import org.junit.jupiter.api.Test;

public class TestRegistration {

    @Test
    public void  testCheckValidUser(){

        RegistrationBean registrationBean = new RegistrationBean();

        registrationBean.setUserName("a");
        registrationBean.setPassword("a");
        registrationBean.setEmail("a");
        registrationBean.setName("a");
        assert  registrationBean.checkValidUser() == "failure": "Existing user failure";
        registrationBean.setUserName("A");
        registrationBean.setPassword("a");
        assert  registrationBean.checkValidUser() == "failure";
        registrationBean.setUserName("nonValidUser");
        registrationBean.setPassword("ZZZZZ");
        registrationBean.setEmail("AAAAAAA");
        registrationBean.setName("AAAAAAAAA");
        assert  registrationBean.checkValidUser() == "itemPage";
    }

}
