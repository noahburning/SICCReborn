package com.ana.api.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_date")
    private Date productId;

    public Order() {
    }

    public Order(Long userId, Date productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getProductId() {
        return productId;
    }

    public void setProductId(Date productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order {" + "id=" + id + ", userId=" + userId + ", productId=" + productId + '}';
    }

}
