package com.sdacademy.twitter.utilis;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public final class HibernateUtilis {


    private HibernateUtilis() {
    }

    private final static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private static Session session;

    /**
     * Method returns Hibernate Session instance
     *
     * @return
     */
    public static Session getHibernateSession() {
        if (session == null) {
            session = sf.openSession();
        }
        return session;
    }
}

