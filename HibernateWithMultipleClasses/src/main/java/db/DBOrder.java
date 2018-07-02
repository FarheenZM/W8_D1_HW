package db;

import models.Customer;
import models.Order;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBOrder {

    private static Session session;
    private static Transaction transaction;

    public static void save(Order order){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();

        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public static List<Order> getAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Order> results = null;

        try {
            Criteria criteria = session.createCriteria(Order.class);
            results = criteria.list();
        } catch(HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static Order find(int id){
        Order result = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("id", id));
            result = (Order) criteria.uniqueResult();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public static void delete(Order order) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria criteria = session.createCriteria(Order.class);
            List<Order> results = criteria.list();
            transaction = session.beginTransaction();
            for(Order order : results){
                session.delete(order);
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}
