package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by lzz on 2018/7/14.
 */

public class SelectBuyerItemVm {
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> phoneNumber = new ObservableField<>("");
    public final ObservableField<String> address = new ObservableField<>("");
    public final ObservableField<Boolean> isSelected = new ObservableField<>(false);
    private Buyer buyer;

    public SelectBuyerItemVm(Buyer buyer) {
        this.buyer = buyer;
        name.set(buyer.getName());
        phoneNumber.set(buyer.getPhoneNumber());
        address.set(buyer.getAddress());
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void itemClick(View view) {
        ((SelectBuyerActivity)view.getContext()).refresh(this);
    }
}
