package main.java.sessionbeans;

import main.java.ejb.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean(name="ItemsBean")
public class ItemsBean {

    private String username;

    public String getUsername() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        return ((User) session.getAttribute("user")).getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
