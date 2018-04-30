package com.sdacademy.twitter.services;

import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao;

    public RegisterServlet() {
        userDao = UserDao.get__instance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String email = req.getParameter("email");
        final String pass1 = req.getParameter("pass1");
        final String nick = req.getParameter("nick");
        final String pass2 = req.getParameter("pass2");

        if (isNullOrEmpty(email)) {
            resp.sendRedirect("register.jsp?err=nomail");
            return;
        }
        if (isNullOrEmpty(nick)) {
            resp.sendRedirect("register.jsp?err=nonick");
            return;
        }

        if (isNullOrEmpty(pass1) || isNullOrEmpty(pass2)) {
            resp.sendRedirect("register.jsp?err=nopass");
            return;
        }

        if (!pass1.equals(pass2)) {
            resp.sendRedirect("register.jsp?err=passnotmatch");
            return;
        }

        Optional<User> id = userDao.add(User.builder().email(email).password(pass1).nick(nick).creationTs(System.currentTimeMillis()).build());
        resp.addCookie(new Cookie(UserDao.USER_SESSION, id.get().getId().toString()));   // added to unable other users edit/remove sb other tweets
        resp.sendRedirect("index.jsp");
    }
    private boolean isNullOrEmpty(final String val) {
        return (val == null || val == "");
    }
}
