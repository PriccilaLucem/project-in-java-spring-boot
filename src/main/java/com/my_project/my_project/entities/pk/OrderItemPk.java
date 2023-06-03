package com.my_project.my_project.entities.pk;

import com.my_project.my_project.entities.Order;
import com.my_project.my_project.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItemPk order(Order order) {
        setOrder(order);
        return this;
    }

    public OrderItemPk product(Product product) {
        setProduct(product);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItemPk)) {
            return false;
        }
        OrderItemPk orderItemPk = (OrderItemPk) o;
        return Objects.equals(order, orderItemPk.order) && Objects.equals(product, orderItemPk.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }

    @Override
    public String toString() {
        return "{" +
                " order='" + getOrder() + "'" +
                ", product='" + getProduct() + "'" +
                "}";
    }

}
