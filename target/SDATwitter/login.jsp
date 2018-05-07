<%--
  Created by IntelliJ IDEA.
  User: Maciej
  Date: 15-04-2018
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter Login</title>
</head>
<body style="background-color:yellowgreen;text-align:center">

<%
    java.util.Date today = new java.util.Date();
    String text = "Today's date is: " + today.toString();
%>
<div style="top: 10px">
    <%=text%>
</div>
</br>

<table align="center">
    <form action="login" method="post">
        <tr>
            <td>Login:</td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="pass" id="pass"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" id="submit" name="submit" value="Login"></td>
        </tr>
    </form>
</table>
<h3><a href="index.jsp">Back</a></h3>
</body>
</html>