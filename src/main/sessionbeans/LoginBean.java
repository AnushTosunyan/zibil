package main.sessionbeans;

//package com.javawebtutor.jsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="LoginBean")
@SessionScoped

public class LoginBean {
    private String userName;
    private String password;
    private String dbuserName;

    private String dbpassword;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;


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

    public String getDbuserName() {
        return dbuserName;
    }

    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public void dbData(String userName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwt","root","mukesh");
            statement = connection.createStatement();
            SQL = "Select * from USER_DETAIL where username like ('" + userName +"')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            dbuserName = resultSet.getString(1).toString();
            dbpassword = resultSet.getString(2).toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }

    public String checkValidUser() {
        return "failure";
    }
//        dbData(userName);
//
//        if(userName.equalsIgnoreCase(dbuserName))
//        {
//
//            if(password.equals(dbpassword))
//                return "success";
//            else
//            {
//                return "failure";
//            }
//        }
//        else
//        {
//            return "failure";
//        }
//    }
}
