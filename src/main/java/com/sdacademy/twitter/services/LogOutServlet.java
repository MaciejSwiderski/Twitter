package com.sdacademy.twitter.services;

import com.sdacademy.twitter.data.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="logoutServlet",value="/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie(UserDao.USER_SESSION,"0");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("index.jsp");
    }
}
