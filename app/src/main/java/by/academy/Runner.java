package by.academy;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = User.newBuilder()
                .withFio("fio")
                .withUserName("name")
                .withAge(13)
                .withUserType(UserType.COACH)
                .withPassword("password")
                .build();
        user = session.find(User.class, user);
        System.out.println(user);
        transaction.commit();
        session.close();
    }
}
