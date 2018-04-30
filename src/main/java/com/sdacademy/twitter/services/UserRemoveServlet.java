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
import java.util.List;
import java.util.Optional;


@WebServlet(name = "userRemove",value="/userRemove")
public class UserRemoveServlet extends HttpServlet {


    private UserDao userDao;
    private TweetDao tweetDao;
    private CommentDao commentDao;

    public UserRemoveServlet() {

        tweetDao = TweetDao.get_instance();
        userDao = UserDao.get__instance();
        commentDao=CommentDao.get_instance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String id = req.getParameter("userId");
        final Optional<Long> userIdd = Utils.getUserIdFromSession(req);

        //final String idc = req.getParameter("idComents");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        if (id != null) {
            final Long lId = Long.valueOf(id);

            Optional<List<Tweet>> tweet = tweetDao.getAll();
            if (tweet.isPresent() && userIdd.isPresent()) {
                for (int i = 0; i < tweet.get().size(); i++) {
                    if (tweet.get().get(i).getUser().getId().equals(userIdd.get())) {
                        tweetDao.remove(tweet.get().get(i));
                    }
                }
            }

                Optional<List<Comments>> comments = commentDao.getAll();
                if (comments.isPresent() && userId.isPresent()) {
                    for(int j=0;j<comments.get().size();j++) {
                        if ((comments.get().get(j).getAuthor().getId().equals(userId.get()))) {  //||((userId.get().equals(comments.get().getTweet().getUser().getId())))
                            commentDao.remove(comments.get().get(j));
                        }
                    }
                }


            Optional<User> user = userDao.get(lId);
            if (user.isPresent() && userIdd.isPresent()) {
                if (user.get().getId().equals(userIdd.get())) {
                    userDao.remove(user.get());
                }
            }
            resp.sendRedirect("logout");
        }
    }
}


