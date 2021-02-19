package by.academy.repository;

import org.hibernate.Session;

public abstract class AbstractHibernateRepository {
    public void closeSession(Session session) {
        if ((null != session) && (session.isOpen())) {
            session.close();
        }
    }
}
