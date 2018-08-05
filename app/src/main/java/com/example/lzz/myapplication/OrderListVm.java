package com.example.lzz.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderListVm {
    private OrderDao orderDao;

    public OrderListVm() {
        orderDao = LApplication.it().getDaoSession().getOrderDao();
    }

    public List<OrderItemVm> queryOrders() {
        List<OrderItemVm> orderItemVms = new ArrayList<>();
        List<Order> orders = orderDao.queryBuilder()
                .orderDesc(OrderDao.Properties.Date)
                .list();

        if (orders != null) {
            for (Order order : orders) {
                OrderItemVm item = new OrderItemVm(order);
                orderItemVms.add(item);
            }
        }
        return orderItemVms;
    }


}
