package com.sdacademy.twitter.data;

import com.sdacademy.twitter.model.Tweet;

public final class TweetDao extends AbstractDao<Tweet>{

    private static TweetDao _instance;

    public static TweetDao get_instance() {
        if (_instance == null) {
            _instance = new TweetDao();
        }
        return _instance;
    }

    private TweetDao(){
        super();
    }

    @Override
    protected Class<Tweet> getClazz() {
            return Tweet.class;
    }
}
