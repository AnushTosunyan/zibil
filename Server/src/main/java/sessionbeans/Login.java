package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.Remote;
import javax.enterprise.inject.Default;

@Remote(Login.class)
public interface Login {
    String checkValidUser(String username, String password);

    User getCurrentUser();
}

