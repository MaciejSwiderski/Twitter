package com.sdacademy.twitter.services;


import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utilis.Utils;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.mysql.cj.core.util.StringUtils.isNullOrEmpty;

@WebServlet(name = "userEdit", value = "/userEdit")
public class UserEdit extends HttpServlet {

    private UserDao userDao;

    public  UserEdit(){
        userDao =UserDao.get__instance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final  String email = req.getParameter("email");
        final  String nick = req.getParameter("nick");
        final  String password = req.getParameter("password");
        final  Long id = Long.valueOf(req.getParameter("userId"));


        if (isNullOrEmpty(email)) {
            resp.sendRedirect("userEdit.jsp?err=nomail");
            return;
        }
        if (isNullOrEmpty(nick)) {
            resp.sendRedirect("userEdit.jsp?err=nonick");
            return;
        }

        if (isNullOrEmpty(password)) {
            resp.sendRedirect("userEdit.jsp?err=nopass");
            return;
        }


        final Optional<Long> userIdd = Utils.getUserIdFromSession(req);

        Optional<User> userFromDb = userDao.get(id);
        if(userFromDb.isPresent()){
            final User user = userFromDb.get();
            if(userIdd.isPresent()){
                if(userIdd.get().equals(user.getId())){
                    user.setEmail(email);
                    user.setNick(nick);
                    user.setPassword(password);
                    userDao.update(user);
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
