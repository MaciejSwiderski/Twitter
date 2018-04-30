<%@ page import="com.sdacademy.twitter.data.UserDao" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.TweetDao" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.sdacademy.twitter.model.Tweet" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdacademy.twitter.model.User" %>
<%@ page import="com.sdacademy.twitter.model.Comments" %>
<%@ page import="com.sdacademy.twitter.data.CommentDao" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<html>
<head>

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 3px;
        }

    </style>
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
    Utils.activateSessione(request);
    UserDao userDao = UserDao.get__instance();
    Optional<List<User>> allUsers = userDao.getAll();
    if (allUsers.isPresent()) {
        request.setAttribute("usersList", allUsers.get());
    }
%>


<%
    Utils.activateSessione(request);
    CommentDao commentDao = CommentDao.get_instance();
    Optional<List<Comments>> allComments = commentDao.getAll();
    if (allComments.isPresent()) {
        request.setAttribute("commentsList", allComments.get());
    }
%>

<%
    Utils.activateSessione(request);
    TweetDao tweetDao = TweetDao.get_instance();
    Optional<List<Tweet>> allTwitts = tweetDao.getAll();


    if (allTwitts.isPresent()) {
        request.setAttribute("tweetList", allTwitts.get());
    }
%>


<c:if test="${sessionOk}">

    </br>
    <c:forEach items="${usersList}" var="users">
        <c:if test="${(userId == users.id)}">
            <div style="font-weight: bold;color: navy;text-align:right;right: 20px;top: 10px;position: fixed">logged in
                : <c:out value="${users.nick}"/></div>
        </c:if>
    </c:forEach>

    <tr style="font-size: large">

        <td><a href="logout">LogOut</a></td>
        <td>&ensp;</td>
        <td><a href="userEdit.jsp">Edit user</a></td>
        <td>&ensp;</td>
        <td><a href="userRemove.jsp">Remove user</a></td>

    </tr>
    </br>


</c:if>
<div>
    <c:if test="${!sessionOk}">

        <div style="font-weight: bold;color: navy;text-align:right;right: 20px;top: 10px;position: fixed">No user logged
            in
        </div>

        </br>
        <tr>
            <td><a href="login.jsp">Login</a></td>
            <td>&ensp;</td>
            <td><a href="register.jsp">Register</a></td>
        </tr>

    </c:if>
</div>

<div>
    <h1>Twitter</h1>

    <h3><a href="addTwitt.jsp">add tweet</a></h3>

    <table border="1px" align="center" style="text-align: center;background: lightgray">

        <tr style="font-weight: bold;font-size: 17px">
            <td>Author</td>
            <td>Message</td>
            <td>Published</td>
            <c:if test="${sessionOk}">
                <td>Options</td>
                <td>Comments</td>
            </c:if>
        </tr>

        <c:forEach items="${tweetList}" var="tweet">

            <tr>
                <td style="font-weight: bold"><c:out value="${tweet.user.nick}"/></td>
                <td style="font-weight: bold"><c:out value="${tweet.message}"/></td>

                <jsp:useBean id="myDate" class="java.util.Date"/>
                <c:set target="${myDate}" property="time" value="${tweet.creationTs}"/>
                <td><c:out value="${myDate}"/></td>


                <c:if test="${sessionOk}">
                    <c:if test="${userId == tweet.user.id}">
                        <td>
                            <a style="font-weight: bold" href='removeTwitt?id=<c:out value="${tweet.id}"/>'>Remove</a>
                            <a style="font-weight: bold" href='updateTwitt.jsp?id=<c:out value="${tweet.id}"/>'>Edit</a>
                        </td>
                    </c:if>
                    <c:if test="${userId != tweet.user.id}">
                        <td>&nbsp;</td>
                    </c:if>
                </c:if>
                <c:if test="${sessionOk}">

                    <td>
                        <a style="font-weight: bold" href='addComment.jsp?id=<c:out value="${tweet.id}"/>'>Comment</a>
                    </td>
                </c:if>
            </tr>
            <tr>
            <c:forEach items="${commentsList}" var="commentsss">
                <c:if test="${tweet.id == commentsss.tweet.id}">
                    <tr style="background: beige">


                        <td style="background: beige"><c:out value="${commentsss.author.nick}"/>
                        <td style="background: beige">
                                <c:out value="${commentsss.message}"/>
                                <jsp:useBean id="myDate1" class="java.util.Date"/>
                                <c:set target="${myDate1}" property="time" value="${commentsss.creationTs}"/>
                        <td style="background: beige"><c:out value="${myDate1}"/></td>


                        <c:if test="${sessionOk}">
                            <c:if test="${(userId == commentsss.author.id)or(userId==tweet.user.id)}">
                                <td style="background: beige">
                                    <a style="background: beige;color: black"
                                       href='removeComment?idComents=<c:out value="${commentsss.id}"/>'>Remove</a>
                                    <c:if test="${(userId == commentsss.author.id)}">
                                        <a style="background: beige;color: black"
                                           href='updateComment.jsp?idComents=<c:out value="${commentsss.id}"/>'>Edit</a>
                                    </c:if>
                                </td>
                            </c:if>
                            <c:if test="${userId != tweet.user.id}">
                                <td>&nbsp;</td>
                            </c:if>
                        </c:if>
                    </tr>

                </c:if>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
