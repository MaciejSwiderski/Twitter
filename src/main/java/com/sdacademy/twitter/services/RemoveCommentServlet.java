package com.sdacademy.twitter.services;

import com.sdacademy.twitter.data.CommentDao;
import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.data.UserDao;
import com.sdacademy.twitter.model.Comments;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.utilis.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "RemoveComments",value ="/removeComment")
public class RemoveCommentServlet extends HttpServlet {

    private CommentDao commentDao;

    public RemoveCommentServlet(){
        commentDao=CommentDao.get_instance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("idComents");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        if (id != null) {
            final Long lId = Long.valueOf(id);
            Optional<Comments> comments = commentDao.get(lId);
            if (comments.isPresent() && userId.isPresent()) {
                if ((comments.get().getAuthor().getId().equals(userId.get()))||((userId.get().equals(comments.get().getTweet().getUser().getId())))) {
                    commentDao.remove(comments.get());
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
