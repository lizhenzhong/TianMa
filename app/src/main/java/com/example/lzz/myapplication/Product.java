package com.example.lzz.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lzz on 2018/7/14.
 */

@Entity
public class Product {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private int type;
    @NotNull
    private String name;
    private String photo;
    private String amount;

    @Generated(hash = 1185877548)
    public Product(Long id, int type, @NotNull String name, String photo,
            String amount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.photo = photo;
        this.amount = amount;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
