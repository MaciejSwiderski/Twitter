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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "removeServlet", value = "/removeTwitt")
public class RemoveServlet extends HttpServlet {

    private TweetDao tweetDao;
    private CommentDao commentDao;

    public RemoveServlet() {
        tweetDao = TweetDao.get_instance();
        commentDao=CommentDao.get_instance();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String id = req.getParameter("id");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        if (id != null) {
            final Long lId = Long.valueOf(id);
            Optional<Tweet> tweet = tweetDao.get(lId);
            Optional<List<Comments>> comment = commentDao.getAll();
            if (tweet.isPresent() && userId.isPresent()) {
                if (tweet.get().getUser().getId().equals(userId.get())) {
                    tweetDao.remove(tweet.get());
                    for(Comments comments:comment.get()){
                        if (((tweet.get().getId().equals(comments.getTweet().getId())))){
                            commentDao.remove(comments);
                        }
                    }
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}

