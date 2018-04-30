package com.sdacademy.twitter.services;


import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utilis.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "updateServlet", value = "/updateTwitt")
public class UpdateServlet extends HttpServlet {

    private TweetDao tweetDao;

    public  UpdateServlet(){
        tweetDao =TweetDao.get_instance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final  String message = req.getParameter("message");
        final  Long id = Long.valueOf(req.getParameter("twittId"));
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        Optional<Tweet> twittFromDb = tweetDao.get(id);
        if(twittFromDb.isPresent()){
            final Tweet tweet = twittFromDb.get();
            if(userId.isPresent()){
                if(userId.get().equals(tweet.getUser().getId())){
                    tweet.setMessage(message);
                    tweetDao.update(tweet);
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
