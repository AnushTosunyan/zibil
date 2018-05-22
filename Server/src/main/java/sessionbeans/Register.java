package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.Remote;

@Remote
public interface Register {
    String checkValidUser(String userName, String password, String email, String name);
    User getFirstUser();

    void deleteUser(String username);

    User getUser();
}
