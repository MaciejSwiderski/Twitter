<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.TweetDao" %>
<%@ page import="com.sdacademy.twitter.data.CommentDao" %>
<%@ page import="com.sdacademy.twitter.model.Comments" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: Maciej
  Date: 28-04-2018
  Time: 01:21
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
    Utils.activateSessione(request);
    CommentDao commentDao = CommentDao.get_instance();
    Optional<List<Comments>> allComments = commentDao.getAll();
    if (allComments.isPresent()) {
        request.setAttribute("commentsList", allComments.get());
    }
%>

<%
    Utils.activateSessione(request);
    CommentDao commentDao2 = CommentDao.get_instance();
    Long idd = Long.valueOf(request.getParameter("idComents"));
    request.setAttribute("commentsss", commentDao2.get(idd).get());
%>

<h3>Update Comment</h3>

<c:if test="${!sessionOk}">
    <h3 style="color:red">Need to log in</a> </h3>
    <h3><a href="index.jsp">Back</a></h3>
</c:if>

<c:if test="${sessionOk}">
    <c:choose>
        <c:when test="${commentsss !=null}">
            <table align="center">
                <form method="post" action="/updateComment">
                    <tr>
                        <td><textarea name="message"><c:out value="${commentsss.message}"/></textarea>
                    <tr>
                        <td><input type="hidden" name="idComents" value='<c:out value="${commentsss.id}"/>'></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" value="Save"/></td>
                    </tr>
                </form>
            </table>
        </c:when>

        <c:otherwise>
            <h3 style="color: blue">Comment with the Id provided does't exist </h3>
        </c:otherwise>
    </c:choose>
</c:if>

<h3><a href="index.jsp">Back</a></h3>

</body>
</html>
