package main.sessionbeans;

import javax.ejb.Remote;

@Remote
public interface Login {
    String getUserName();
    String getPassword();
    void setUserName(String userName);
    void setPassword(String pass);
    String checkValidUser();

}

