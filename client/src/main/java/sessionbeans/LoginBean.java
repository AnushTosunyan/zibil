
package main.java.sessionbeans;

import main.java.ejb.User;
import org.jglue.cdiunit.AdditionalClasses;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean(name="LoginBean")
@AdditionalClasses(RemoteLoginBean.class)
public class LoginBean {

    @Inject
    private Login login;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String checkValidUser() {
        String answer = login.checkValidUser(userName, password);

        User user = login.getCurrentUser();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        session.setAttribute("user", user);
        return answer;
    }
}
