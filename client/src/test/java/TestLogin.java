package test.java;

import main.java.sessionbeans.LoginBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

class TestLogin{

//    @Inject private
//    LoginBean login;

    @Test
    public void  testCheckValidUser(){
        LoginBean login = new LoginBean();

        login.setUserName("a");
        login.setPassword("a");
        assert  login.checkValidUser() == "itemPage": "Valid user failure";
        login.setUserName("A");
        login.setPassword("a");
        assert  login.checkValidUser() == "itemPage";
        login.setUserName("nonValidUser");
        login.setPassword("ZZZZZ");
        assert  login.checkValidUser() == "failure";
        login.setUserName("a");
        login.setPassword("A");
        assert  login.checkValidUser() == "failure";
    }

}