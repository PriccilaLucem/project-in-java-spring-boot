package com.my_project.my_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my_project.my_project.entities.pk.OrderItemPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();

    private Integer quantity;
    private Double price;

    public OrderItem() {

    }

    public OrderItem(Product product, Order order, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);

        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return this.price * this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItem)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
