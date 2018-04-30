<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.sdacademy.twitter.utilis.Utils" %>
<%@ page import="com.sdacademy.twitter.data.TweetDao" %><%--
Created by IntelliJ IDEA.
User: Maciej
Date: 20-04-2018
Time: 20:52
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update twitt</title>
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
    TweetDao tweetDao = TweetDao.get_instance();
    Long id = Long.valueOf(request.getParameter("id"));
    request.setAttribute("twiit", tweetDao.get(id).get());
%>

<h3>Update Message</h3>

<c:if test="${!sessionOk}">
    <h3 style="color:red">Need to log in</a> </h3>
    <h3><a href="index.jsp">Back</a></h3>
</c:if>


<c:if test="${sessionOk}">
    <c:choose>
        <c:when test="${twiit !=null}">
            <table align="center">
                <form method="post" action="/updateTwitt">
                    <tr>
                        <td><textarea name="message"><c:out value="${twiit.message}"/></textarea>
                    <tr>
                        <td><input type="hidden" name="twittId" value='<c:out value="${twiit.id}"/>'></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" value="Save"/></td>
                    </tr>
                </form>
            </table>
        </c:when>
        <c:otherwise>
            <h3 style="color: blue">Twitt with the Id provided does't exist </h3>
        </c:otherwise>
    </c:choose>
</c:if>
<h3><a href="index.jsp">Back</a></h3>

</body>
</html>
