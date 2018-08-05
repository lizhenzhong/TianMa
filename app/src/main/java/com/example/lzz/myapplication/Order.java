package com.example.lzz.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by lzz on 2018/7/14.
 */
@Entity
public class Order {
    @Id(autoincrement = true)
    private Long id;
    private String amount;
    private java.util.Date date;
    private Long buyerId;
    private String buyerName;
    @Generated(hash = 726122574)
    public Order(Long id, String amount, java.util.Date date, Long buyerId,
            String buyerName) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public java.util.Date getDate() {
        return this.date;
    }
    public void setDate(java.util.Date date) {
        this.date = date;
    }
    public Long getBuyerId() {
        return this.buyerId;
    }
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
    public String getBuyerName() {
        return this.buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }


}
