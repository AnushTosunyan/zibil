package main.java.ejb;

import javax.ejb.Remote;

@Remote
public interface Login {
    void setUserName(String userName);
    void setPassword(String pass);
    String checkValidUser();
}
