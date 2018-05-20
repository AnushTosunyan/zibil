package main.java.sessionbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="WelcomeBean")
public class WelcomeBean {

    public String login(){
        return "login";
    }
    public String register(){
        return "registration";
    }
}
