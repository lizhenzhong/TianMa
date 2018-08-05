package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

/**
 * Created by lzz on 2018/7/14.
 */

public class BuyersItemVm {
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> phoneNumber = new ObservableField<>("");
    public final ObservableField<String> address = new ObservableField<>("");
    private Buyer buyer;

    public BuyersItemVm(Buyer buyer) {
        this.buyer = buyer;
        name.set(buyer.getName());
        phoneNumber.set(buyer.getPhoneNumber());
        address.set(buyer.getAddress());
    }

    public void itemClick(View view) {
        if (buyer != null) {
            AddBuyerActivity.skipToAddBuyerAct(view.getContext(), buyer.getId());
        }
    }
}
