package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.text.format.DateFormat;
import android.view.View;

import java.util.Date;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderItemVm {
    public final ObservableField<String> orderId = new ObservableField<>("");
    public final ObservableField<String> orderDate = new ObservableField<>("");
    public final ObservableField<String> buyerName = new ObservableField<>("");
    public final ObservableField<String> amount = new ObservableField<>("");
    private Order order;

    public OrderItemVm(Order order) {
        this.order = order;
        orderId.set(LApplication.it().getString(R.string.order_id, String.valueOf(order.getId())));
        orderDate.set(format(order.getDate()));
        buyerName.set(order.getBuyerName());
        amount.set(LApplication.it().getString(R.string.car_all_amount, order.getAmount()));
    }

    private String format(Date date) {
        return DateFormat.format("yyyy年MM月dd日 HH:mm", date).toString();
    }

    public void itemClick(View view) {
        OrderDetailsActivity.skipToOrderDetailsAct(view.getContext(), order.getId());
    }
}
