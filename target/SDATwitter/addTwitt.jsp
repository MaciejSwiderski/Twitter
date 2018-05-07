<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.sdacademy.twitter.data.UserDao" %>
<%@ page import="com.sdacademy.twitter.model.User" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %><%--

  Created by IntelliJ IDEA.
  User: Maciej
  Date: 16-04-2018
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add Twitt</title>

</head>

<body style="background-color:yellowgreen;text-align:center">

<%
    java.util.Date today = new java.util.Date();
    String text = "Today's date is: " + today.toString();
%>
<div style="top: 10px">
    <%=text%>
</div>

<%Utils.activateSessione(request);%>

<h3>Add Tweet</h3>


<c:if test="${!sessionOk}">
    <h3 style="color:red">Need to log in</a> </h3>
    <h3><a href="index.jsp">Back</a></h3>
</c:if>


<c:if test="${sessionOk}">

    <table align="center">
        <form method="post" action="addTwitt">
            <tr>
                <td><textarea name="message"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Add Tweet"/></td>
            </tr>
        </form>
    </table>
    <h3><a href="index.jsp">Back</a></h3>

</c:if>

</body>
</html>

