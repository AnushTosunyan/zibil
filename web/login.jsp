<%--
  Created by IntelliJ IDEA.
  User: anush_tosunyan
  Date: 4/28/18
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h:form>
    <table>
        <tr>
            <td><h:outputText value="Enter Login ID: " /></td>
            <td><h:inputText id="userName"
                             value="#{LoginBean.user_name}" />
            </td>
        </tr>
        <tr>
            <td><h:outputText value="Enter Password: " /></td>
            <td><h:inputSecret id="password"
                               value="#{LoginBean.password}" />
            </td>
        </tr>
        <tr>
            <td> </td>
            <td><h:commandButton value="Login"
                                 action="#{LoginBean.checkValidUser}"/>
            </td>
        </tr>
    </table>
</h:form>
</body>
</html>
