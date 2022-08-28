package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sf = HibernateSessionFactory.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS User (id INT primary key NOT NULL AUTO_INCREMENT, user_name VARCHAR(50), last_name VARCHAR(50), age INT)";
            Query query = session.createSQLQuery(sqlCreateTable).addEntity(User.class);
            query.executeUpdate();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sf = HibernateSessionFactory.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS User";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sf = HibernateSessionFactory.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sf = HibernateSessionFactory.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String sqlRemove = String.format("delete from User where id =%d", 1);
            Query query = session.createSQLQuery(sqlRemove).addEntity(User.class);
            query.executeUpdate();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        SessionFactory sf = HibernateSessionFactory.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String sql = "select * from User";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            listUser  = query.getResultList();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String sql = "delete from User";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.close();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
        }
    }
}

