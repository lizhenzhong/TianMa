package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderDetailsVm {
    private OrderDao orderDao;
    private BuyerDao buyerDao;
    private OrderDetailsDao detailsDao;

    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> phoneNumber = new ObservableField<>("");
    public final ObservableField<String> address = new ObservableField<>("");
    public final ObservableField<String> orderDate = new ObservableField<>("");
    public final ObservableField<String> amount = new ObservableField<>("");

    public OrderDetailsVm() {
        orderDao = LApplication.it().getDaoSession().getOrderDao();
        buyerDao = LApplication.it().getDaoSession().getBuyerDao();
        detailsDao = LApplication.it().getDaoSession().getOrderDetailsDao();
    }

    public void setOrderInfo(long orderId) {
        List<Order> orders = orderDao.queryBuilder()
                .where(OrderDao.Properties.Id.eq(orderId))
                .list();
        if (!orders.isEmpty()) {
            Order order = orders.get(0);
            orderDate.set(format(order.getDate()));
            amount.set(LApplication.it().getString(R.string.car_all_amount, order.getAmount()));

            List<Buyer> buyers = buyerDao.queryBuilder()
                    .where(BuyerDao.Properties.Id.eq(order.getBuyerId()))
                    .list();
            if (!buyers.isEmpty()) {
                Buyer buyer = buyers.get(0);
                name.set(buyer.getName());
                phoneNumber.set(buyer.getPhoneNumber());
                address.set(buyer.getAddress());
            }
        }
    }

    public List<OrderDetailsItemVm> queryOrderDetails(long orderId) {
        List<OrderDetailsItemVm> orderDetailsItemVms = new ArrayList<>();
        List<OrderDetails> detailses = detailsDao.queryBuilder()
                .where(OrderDetailsDao.Properties.OrderId.eq(orderId))
                .list();
        for (OrderDetails details : detailses) {
            OrderDetailsItemVm itemVm = new OrderDetailsItemVm(details);
            orderDetailsItemVms.add(itemVm);
        }
        return orderDetailsItemVms;
    }

    private String format(Date date) {
        return DateFormat.format("yyyy年MM月dd日 HH:mm", date).toString();
    }
}
