package com.sanedge.base.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long customerId;
    private Long productId;
    private int productCount;
    private int price;
    private String status;
    private String source;

    public Order(Long id, Long customerId, Long productId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.status = status;
    }

    public Order(Long id, Long customerId, Long productId, int productCount, int price) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productCount = productCount;
        this.price = price;
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productCount=" + productCount +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}