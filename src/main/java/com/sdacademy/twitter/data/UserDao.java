package com.sdacademy.twitter.data;

import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utilis.HibernateUtilis;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;


public final class UserDao extends AbstractDao<User>{

    private static UserDao __instance;

    public  static final  String USER_SESSION ="mysessioncookie";

    public static UserDao get__instance() {
        if (__instance == null) {
            __instance = new UserDao();
        }
        return __instance;
    }

    private UserDao(){
        super();
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }

    public Optional<User> checkUserByEmailAndPassword(final @NonNull String email, final @NonNull String password) {
        Session session = HibernateUtilis.getHibernateSession();
        try{
            String hql = "from User u where upper(u.email) = upper(:email) and u.password = :password";
            List<User> users = session.createQuery(hql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .list();

            if (users.size() == 1) {
                return Optional.of(users.get(0));
            }
            return Optional.empty();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
