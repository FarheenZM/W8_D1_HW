package db;

import models.Customer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBCustomer {

    private static Session session;
    private static Transaction transaction;


    public static void save(Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void update(Customer customer){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public static List<Customer> getAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Customer> results = null;

        try {
            Criteria criteria = session.createCriteria(Customer.class);
            results = criteria.list();
        } catch(HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }



    public static void listAll(){
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> results = criteria.list();
            for(Customer customer : results){
                System.out.println(customer.getFirstName() + " " + customer.getLastName());
            }
        } catch(HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Customer find(int id){
        Customer result = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("id", id));
            result = (Customer) criteria.uniqueResult();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public static void delete(Customer customer) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(customer);
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
            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> results = criteria.list();
            transaction = session.beginTransaction();
            for(Customer customer : results){
                session.delete(customer);
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
