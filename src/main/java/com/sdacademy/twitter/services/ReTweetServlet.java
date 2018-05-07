package com.sdacademy.twitter.services;


import com.sdacademy.twitter.data.CommentDao;
import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.Comments;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utilis.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@WebServlet(name = "reTweet", value = "/retweet")
public class ReTweetServlet extends HttpServlet {


    private TweetDao tweetDao;
    private UserDao userDao;
    private CommentDao commentDao;


    public ReTweetServlet() {
        tweetDao = TweetDao.get_instance();
        userDao = UserDao.get__instance();
        commentDao = CommentDao.get_instance();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String reTweetMessage = req.getParameter("message");
        final Long id = Long.valueOf(req.getParameter("twittId"));
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        Optional<Tweet> tweetFromDb = tweetDao.get(id);


        if (tweetFromDb.isPresent()) {
            Optional<User> user = userDao.get(userId.get());

            if (tweetFromDb.get().getId().equals(id)) {
                final Tweet tweet = Tweet.builder()
                        .message(tweetDao.get(id).get().getMessage())
//                        .user(user.get())
                        .user(tweetFromDb.get().getUser())
                        .creationTs(System.currentTimeMillis())
                        .idReTweets(tweetFromDb.get())
                        .build();

                tweetDao.add(tweet);


                if (userId.isPresent()) {
                    final Comments comments = Comments.builder()
                            .message(reTweetMessage  +" \"retweeted by: "+user.get().getNick()+"\"")
                            .author(user.get())
                            .creationTs(System.currentTimeMillis())
                            .tweet(tweet)
                            .build();
//                    if (reTweetMessage != "") {
                        commentDao.add(comments);
//                    }
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
