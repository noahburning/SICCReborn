package com.ana.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "cookie_id")
    private Long cookieId;

    @Column(name = "quantity")
    private Long quantity;

    public OrderItem() {
    }

    public OrderItem(Long orderId, Long cookieId, Long quantity) {
        this.orderId = orderId;
        this.cookieId = cookieId;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCookieId() {
        return cookieId;
    }

    public void setCookieId(Long cookieId) {
        this.cookieId = cookieId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem {" + "id=" + id + ", orderId=" + orderId + ", cookieId=" + cookieId + ", quantity=" + quantity + '}';
    }

}
