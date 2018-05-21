package main.java.sessionbeans;

import javax.ejb.Remote;

@Remote(Login.class)
public interface Login {
    String checkValidUser(String username, String password);

}

