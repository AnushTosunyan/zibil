package main.sessionbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.*;
import javax.sql.DataSource;



@ManagedBean(name="RegistrationBean")
@SessionScoped

public class RegistrationBean {
    private String name;
    private String userName;
    private String password;
    private String email;

    private String dbuserName;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

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

    public String getDbuserName() {
        return dbuserName;
    }

    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }

    public void dbData(String userName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwt","root","mukesh");
            statement = connection.createStatement();

            SQL = "Select * from userDB where user_name like ('" + userName +"')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            dbuserName = resultSet.getString(1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }
    }

    public String checkValidUser() {

        dbData(userName);

        if(userName.equalsIgnoreCase(dbuserName))
        {
            return "failure";
        }
        else
            try {
                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bd");

                if (ds != null) {
                    Connection con = ds.getConnection();
                    if (con != null) {
                        String sql = "INSERT INTO userDB(name, userName, password, email) VALUES(?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, password);
                        ps.setString(3, userName);
                        ps.setString(4, email);
                        ps.executeUpdate();
                        System.out.println("Data Added Successfully");
                        return "success";
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Exception Occurred in the process :" + ex);
            }
        return  "failure";
    }
}

