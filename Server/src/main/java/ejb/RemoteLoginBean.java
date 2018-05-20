package main.java.ejb;

import javax.ejb.Stateless;

@Stateless
public class RemoteLoginBean implements Login {
    @Override
    public void setUserName(String userName) {

    }

    @Override
    public void setPassword(String pass) {

    }

    @Override
    public String checkValidUser() {
        return null;
    }
}
