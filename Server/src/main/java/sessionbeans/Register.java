package main.java.sessionbeans;

import javax.ejb.Remote;

@Remote
public interface Register {
    String checkValidUser(String userName, String password, String email, String name);
}
