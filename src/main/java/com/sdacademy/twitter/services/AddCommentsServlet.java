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
import java.util.Optional;


@WebServlet(name = "AddComments", value = "/addComment")
public class AddCommentsServlet extends HttpServlet {

    private TweetDao tweetDao;
    private UserDao userDao;
    private CommentDao commentDao;

    public AddCommentsServlet() {
        tweetDao = TweetDao.get_instance();
        userDao = UserDao.get__instance();
        commentDao = CommentDao.get_instance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String message = req.getParameter("message");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);
        final Long idTwiit = Long.valueOf(req.getParameter("twittId"));

        if (userId.isPresent()&&(message!="")) {
            Optional<User> user = userDao.get(userId.get());
            Optional<Tweet> tweet = tweetDao.get(idTwiit);

            final Comments comments = Comments.builder()
                    .message(message)
                    .author(user.get())
                    .creationTs(System.currentTimeMillis())
                    .tweet(tweet.get())
                    .build();
            commentDao.add(comments);
        }
        resp.sendRedirect("index.jsp");
    }
}
