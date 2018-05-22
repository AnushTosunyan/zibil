
package main.java.sessionbeans;

import main.java.sessionbeans.Login;

import java.util.Hashtable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import static javax.naming.Context.URL_PKG_PREFIXES;

@SessionScoped
@ManagedBean(name="LoginBean")
public class LoginBean {
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
        String answer = "failure";
        try {
            final Login login = lookupLogin();

            answer = login.checkValidUser(userName, password);
        } catch (NamingException e) {
            System.out.println("Error during lookup");
            e.printStackTrace();
        }

        return answer;
    }

    private static Login lookupLogin() throws NamingException {
        try {
            Hashtable<Object, Object> props = new Hashtable<>();

            props.put(INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

            props.put("jboss.naming.client.ejb.context", false);
            props.put("org.jboss.ejb.client.scoped.context", true);

            props.put(PROVIDER_URL, "remote://" + "localhost" + ":" + 8080);
            props.put("remote.connection.default.host", "localhost");
            props.put("remote.connection.default.port", 8080);

            Context ctx = new InitialContext(props);

            String appName = "";
            String moduleName = "Server";
            String beanName = RemoteLoginBean.class.getSimpleName();
            String interfaceName = Login.class.getName();
            String lookupString = "ejb:/Server/RemoteLoginBean!main.java.sessionbeans.Login";
            return (Login) ctx.lookup(lookupString);

        }
        catch (NamingException e){
            throw e;
        }
    }

//    public void dbData() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            // change db name and password accordingly
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","LoveRocK96");
//            statement = connection.createStatement();
//            // change schema, table and property name accordingly
//            SQL = "SELECT * FROM new_schema.users WHERE user_name = ?";
//            PreparedStatement pst = connection.prepareStatement(SQL);
//            pst.setString(1, userName);
//            resultSet = pst.executeQuery();
//            if (resultSet.next()) {
//                dbuserName = resultSet.getString("user_name"); // here too
//                dbpassword = resultSet.getString("password"); // and here
//                dbemail = resultSet.getString("email");
//                dbname = resultSet.getString("name");
//            } else {
//                dbuserName = null;
//                dbpassword = null;
//            }
//        } catch(Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Exception Occured in the process :" + ex);
//        }
//    }
//
//    public String checkValidUser() {
//        dbData();
//
//        if (dbuserName == null || dbpassword == null) {
//            return "failure";
//        }
//        if (userName.equalsIgnoreCase(dbuserName)) {
//                if(password.equals(dbpassword)){
//                    currUser = new User(dbuserName, dbemail, dbpassword, dbname);
//                    return "itemPage";
//                }
//                return"failure";
//        } else {
//            return "failure";
//        }
//    }
}
