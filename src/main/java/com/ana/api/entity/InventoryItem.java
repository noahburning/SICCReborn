package com.ana.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "InventoryItem")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cookie_id")
    private Long cookieId;

    @Column(name = "quantity")
    private Long quantity;

    public InventoryItem() {
    }

    public InventoryItem(Long cookieId, Long quantity) {
        this.cookieId = cookieId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "InventoryItem {" + "id=" + id + ", cookieId=" + cookieId + ", quantity=" + quantity + '}';
    }
}
