package main.java.sessionbeans;

import javax.ejb.Remote;

@Remote
public interface Register {
    void setUserName(String userName);
    void setPassword(String pass);
    void setEmail(String userName);
    void setName(String pass);
    String checkValidUser();
}
