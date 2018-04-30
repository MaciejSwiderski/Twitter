<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <!-- Bootstrap CSS -->
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"--%>
    <%--integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">--%>
    <title>SDA Twitter Registration Form</title>
</head>
<body style="background-color:yellowgreen;text-align:center">
<%
    java.util.Date today = new java.util.Date();
    String text = "Today's date is: " + today.toString();
%>
<div style="top: 10px">
    <%=text%>
</div>
<h3>Register to SDA Twitter</h3>
<div>
    <c:set var="error" value="${param['err']}"/>
    <h6 style="color: red;font-size: 18px;">
        <c:choose>
            <c:when test="${error eq 'nomail'}">No email address provided</c:when>
            <c:when test="${error eq 'nonick'}">No nick provided</c:when>
            <c:when test="${error eq 'nopass'}">No password provided</c:when>
            <c:when test="${error eq 'passnotmatch'}">Passwords don't match</c:when>
        </c:choose>
    </h6>

    <table align="center">
        <form action="register" method="post">
            <tr>
                <td>Login:</td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Nick:</td>
                <td><input type="text" name="nick" id="nick"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="pass1" id="pass1"></td>
            </tr>
            <tr>
                <td>Repeat Password:</td>
                <td><input type="password" name="pass2" id="pass2"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" id="submit" name="submit" value="Register"></td>
            </tr>

        </form>
    </table>
    <h3><a href="index.jsp">Back</a></h3>
</body>
</html>