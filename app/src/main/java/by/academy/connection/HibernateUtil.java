package by.academy.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static volatile SessionFactory sessionFactory;

    public static SessionFactory getFactory() {
        if (null == sessionFactory ) {
            synchronized (HibernateUtil.class) {
                if (null == sessionFactory) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}
