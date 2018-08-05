package com.example.lzz.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lzz on 2018/7/14.
 */
@Entity
public class OrderDetails {
    @Id(autoincrement = true)
    private Long id;
    private Long orderId;
    private String amount;
    private Long productId;
    private String productName;
    private Long count;
    @Generated(hash = 1136909477)
    public OrderDetails(Long id, Long orderId, String amount, Long productId,
            String productName, Long count) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.productId = productId;
        this.productName = productName;
        this.count = count;
    }
    @Generated(hash = 2061567955)
    public OrderDetails() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public Long getProductId() {
        return this.productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getCount() {
        return this.count;
    }
    public void setCount(Long count) {
        this.count = count;
    }


}
