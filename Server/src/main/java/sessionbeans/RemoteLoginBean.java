package main.java.sessionbeans;

import javax.ejb.Stateless;

@Stateless
public class RemoteLoginBean implements Login {
    @Override
    public void setPassword(String pass) {

    }

    @Override
    public String checkValidUser() {
        return null;
    }

    @Override
    public void setUserName(String userName) {

    }
}
