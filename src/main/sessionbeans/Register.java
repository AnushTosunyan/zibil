package main.sessionbeans;

import javax.ejb.Remote;

@Remote
public interface Register {
    String getUserName();
    String getPassword();
    String getEmail();
    String getName();
    void setUserName(String userName);
    void setPassword(String pass);
    void setEmail(String userName);
    void setName(String pass);
    String checkValidUser();
}
