package com.sdacademy.twitter.services;

import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utilis.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "twittServlet", value = "/addTwitt")
public class TwittServlet extends HttpServlet {
    private  UserDao userDao;
    private TweetDao tweetDao;

    public  TwittServlet(){
        userDao =UserDao.get__instance();
        tweetDao =TweetDao.get_instance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final  String message = req.getParameter("message");
        final Optional<Long>userId = Utils.getUserIdFromSession(req);

        if(userId.isPresent()) {
            Optional<User> user = userDao.get(userId.get());
           final Tweet tweet = Tweet.builder()
                    .message(message)
                    .user(user.get())
                    .creationTs(System.currentTimeMillis())
                    .build();
            tweetDao.add(tweet);
        }
        resp.sendRedirect("index.jsp");
    }
}
