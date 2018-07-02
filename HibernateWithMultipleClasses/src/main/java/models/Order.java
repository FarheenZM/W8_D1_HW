package models;


import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    private int id;
    private String orderNo;

    public Order(){

    }

    public Order(String orderNo) {
        this.orderNo = orderNo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "order_No")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
