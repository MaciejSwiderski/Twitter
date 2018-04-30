package com.sdacademy.twitter.services;


import com.sdacademy.twitter.data.CommentDao;
import com.sdacademy.twitter.data.TweetDao;
import com.sdacademy.twitter.model.Comments;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.utilis.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "updateComments",value = "/updateComment")
public class UpdateCommentServlet extends HttpServlet {

    private TweetDao tweetDao;
    private CommentDao commentDao;


    public UpdateCommentServlet(){

        tweetDao =TweetDao.get_instance();
        commentDao=CommentDao.get_instance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final  String message = req.getParameter("message");
        final  Long id = Long.valueOf(req.getParameter("idComents"));
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        Optional<Comments> commentFromDb = commentDao.get(id);
        if(commentFromDb.isPresent()){
            final Comments comments = commentFromDb.get();
                if((userId.get().equals(comments.getAuthor().getId()))){  //||((userId.get().equals(comments.getTweet().getUser().getId())))
                    comments.setMessage(message);
                    commentDao.update(comments);
                }
        }
        resp.sendRedirect("index.jsp");
    }
}
