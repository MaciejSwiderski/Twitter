<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.UserDao" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.sdacademy.twitter.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Maciej
  Date: 22-04-2018
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color:yellowgreen;text-align:center">

<%
    java.util.Date today = new java.util.Date();
    String text = "Today's date is: " + today.toString();
%>
<div style="top: 10px">
    <%=text%>
</div>

<%
    UserDao userDao = UserDao.get__instance();
    Optional<Long> userIdFromSession = Utils.getUserIdFromSession(request);
    Optional<User> idf = Optional.of(userDao.get(userIdFromSession.get()).get());
    request.setAttribute("user", userDao.get(idf.get().getId()).get());

%>


<div style="font-weight: bold;color: navy;text-align:right;right: 20px;top: 10px;position: fixed">logged in : <c:out
        value="${user.nick}"/></div>

<h3 style="color: black">User editor</h3>

<c:set var="error" value="${param['err']}"/>
<h6 style="color: red;font-size: 18px;">
    <c:choose>
        <c:when test="${error eq 'nomail'}">No email address provided</c:when>
        <c:when test="${error eq 'nonick'}">No nick provided</c:when>
        <c:when test="${error eq 'nopass'}">No password provided</c:when>
    </c:choose>
</h6>


<c:choose>
    <c:when test="${user!=null}">
        <table align="center">
            <form method="post" action="/userEdit">


                <tr>
                    <td>email:
                <tr>
                    <td><input type="text" name="email"></td>
                </tr>

                <tr>
                    <td>nick
                <tr>
                    <td><input type="text" name="nick"></td>
                </tr>

                <tr>
                    <td>password:
                <tr>
                    <td><input type="password" name="password"></td>
                </tr>

                <tr>
                    <td><input type="hidden" name="userId" value='<c:out value="${user.id}"/>'></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Save"/></td>
                </tr>
            </form>
        </table>
    </c:when>
    <c:otherwise>
        <h3 style="color: blue">User with the Id provided does't exist </h3>
    </c:otherwise>
</c:choose>

<h3><a href="index.jsp">Back</a></h3>

</body>
</html>
