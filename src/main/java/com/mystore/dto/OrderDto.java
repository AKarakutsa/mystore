package com.mystore.dto;

import com.mystore.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderDto {

    private Map<String, ProductDto> products = new HashMap<>();
    private Integer sum;

    public Map<String, ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Map<String, ProductDto> products) {
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
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(products, orderDto.products) &&
                Objects.equals(sum, orderDto.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, sum);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "products=" + products +
                ", sum=" + sum +
                '}';
    }

    public static OrderDto build(Order order) {
        OrderDto orderDto = new OrderDto();
        
        order.getProducts().forEach((id, product) -> {
            orderDto.getProducts().putIfAbsent(id, product == null ? null : ProductDto.build(product));
        });
        orderDto.setSum(order.getSum());

        return orderDto;
    }
}
