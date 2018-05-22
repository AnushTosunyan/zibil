package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;

@SessionScoped
@ManagedBean(name="RegistrationBean")

public class RegistrationBean{
    @EJB
    private Register register;
    private String name;
    private String userName;
    private String password;
    private String email;


    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public String checkValidUser() {
        String answer = register.checkValidUser(userName, password, email, name);
        User user = register.getUser();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

        session.setAttribute("user", user);
        return answer;
    }

}

