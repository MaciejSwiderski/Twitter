package com.sdacademy.twitter.data;

import com.sdacademy.twitter.model.Comments;

public final class CommentDao extends AbstractDao<Comments>{

    private static CommentDao _instance;

    /**
     * Method returns instance of Comment DAO
     *
     * @return the instance
     */
    public static CommentDao get_instance() {
        if (_instance == null) {
            _instance = new CommentDao();
        }
        return _instance;
    }
    private CommentDao(){
        super();
    }

    @Override
    protected Class<Comments> getClazz() {
        return Comments.class;
    }
}
