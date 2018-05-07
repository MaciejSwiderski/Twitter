<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.sdacademy.twitter.model.User" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.UserDao" %>
<%@ page import="com.sdacademy.twitter.model.Tweet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdacademy.twitter.data.TweetDao" %><%--
  Created by IntelliJ IDEA.
  User: Maciej
  Date: 23-04-2018
  Time: 16:40
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
    String text = "Today's date is: "+today.toString();
%>

<div style="top: 10px">
    <%=text%>
</div>

    <%Utils.activateSessione(request);%>
    <%
        UserDao userDao = UserDao.get__instance();
        Optional<Long>userIdFromSession=Utils.getUserIdFromSession(request);
        Optional<User> idf =Optional.of(userDao.get(userIdFromSession.get()).get());
        request.setAttribute("user",userDao.get(idf.get().getId()).get());
    %>

<div style="font-weight: bold;color: navy;text-align:right;right: 20px;top: 10px;position: fixed">logged in : <c:out
        value="${user.nick}"/></div>


    <%
    Utils.activateSessione(request);
    TweetDao tweetDao=TweetDao.get_instance();
    Optional<List<Tweet>>allTwitts=tweetDao.getAll();
    if(allTwitts.isPresent()){
        request.setAttribute("tweetList",allTwitts.get());

    }
%>

<c:if test="${!sessionOk}">
<h3 style="color:red">Need to log in</a> </h3>

</c:if>


<c:if test="${sessionOk}">

<c:choose>
<c:when test="${user!=null}">

<table align="center">
    <form method="post" action="userRemove">

        <tr>
            <td><input type="hidden" name="userId" value='<c:out value="${user.id}"/>'></td>
        </tr>
        <h3 style="color: black">Press to confirm</h3>
        <tr>
            <td><input type="submit" name="submit" value="Remove user"/></td>
        </tr>
        <a href="logout"></a>

    </form>
</table>

</c:when>
<c:otherwise>
<h3 style="color: blue">User with the Id provided does't exist </h3>

</c:otherwise>
</c:choose>

</c:if>

<h3><a href="index.jsp">Back</a></h3>

<body>

</body>
</html>
