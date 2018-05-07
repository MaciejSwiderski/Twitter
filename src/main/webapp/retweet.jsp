<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.TweetDao" %>
<%@ page import="com.sdacademy.twitter.model.Tweet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: Maciej
  Date: 05-05-2018
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReTweet</title>
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
    TweetDao tweetDao1 = TweetDao.get_instance();
    Long id = Long.valueOf(request.getParameter("id"));
    request.setAttribute("twiittt", tweetDao1.get(id).get());
%>

<%
    Utils.activateSessione(request);
    TweetDao tweetDao = TweetDao.get_instance();
    Optional<List<Tweet>> allTwiits = tweetDao.getAll();
    if (allTwiits.isPresent()) {
        request.setAttribute("twittList", allTwiits.get());
    }
%>

<h2>Add comment to ReTweet</h2>

<table align="center">
    <form method="post" action="retweet">
        <tr>
            <td><textarea name="message"></textarea></td>
        </tr>
        <tr>
            <td><input type="hidden" name="twittId" value='<c:out value="${twiittt.id}"/>'></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value="ReTweet"/></td>
        </tr>
    </form>
</table>
<h3><a href="index.jsp">Back</a></h3>


</body>
</html>
