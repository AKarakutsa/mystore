package com.mystore.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {

    /**
     * Products
     */
    private Map<String, Product> products = new HashMap<>();

    /**
     * Products sum
     */
    private Integer sum;

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(products, order.products) &&
                Objects.equals(sum, order.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", sum=" + sum +
                '}';
    }
}
