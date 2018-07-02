package models;

import com.sun.tools.corba.se.idl.constExpr.Or;
import db.DBCustomer;
import db.DBOrder;

import java.util.List;

public class Runner {

    public static void main(String[] args){
        Customer customer1 = new Customer("Eti", "Chandel");
        Customer customer2 = new Customer("Jay", "Sharma");
        Customer customer3 = new Customer("Rina", "Kapoor");
        Customer customer4 = new Customer("Aryan", "Khan");
        Customer customer5 = new Customer("Tia", "Jain");

        DBCustomer.save(customer1);
        DBCustomer.save(customer2);
        DBCustomer.save(customer3);
        DBCustomer.save(customer4);
        DBCustomer.save(customer5);

        Order order1 = new Order("Od101");
        Order order2 = new Order("Od102");
        Order order3 = new Order("Od103");

        DBOrder.save(order1);
        DBOrder.save(order2);
        DBOrder.save(order3);

        List<Customer> allCustomers = DBCustomer.getAll();

        DBCustomer.delete(customer3);
        List<Customer> customers = DBCustomer.getAll();

        customer3.setFirstName("Riya");
        DBCustomer.update(customer3);

        Customer newCustomer = DBCustomer.find(customer1.getId());

        DBCustomer.listAll();

        DBCustomer.deleteAll();
        List<Customer> custListAfterDelete = DBCustomer.getAll();

        List<Order> allOrders = DBOrder.getAll();

        DBOrder.delete(order3);
        List<Order> orders = DBOrder.getAll();

        order2.setOrderNo("Od202");
        DBOrder.save(order2);

        Order newOrder = DBOrder.find(order2.getId());

        DBOrder.deleteAll();
        List<Order> orderListAfterDelete = DBOrder.getAll();

    }

}
