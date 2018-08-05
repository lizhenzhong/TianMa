package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderDetailsItemVm {
    public final ObservableField<Uri> photo = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> count = new ObservableField<>("");
    private OrderDetails details;

    public OrderDetailsItemVm(OrderDetails details) {
        this.details = details;
        ProductDao productDao = LApplication.it().getDaoSession().getProductDao();
        List<Product> products = productDao.queryBuilder()
                .where(ProductDao.Properties.Id.eq(details.getProductId()))
                .list();
        if (!products.isEmpty()) {
            Product product = products.get(0);
            photo.set(Uri.parse(product.getPhoto()));
            name.set(LApplication.it().getString(R.string.car_product_price, product.getName(), details.getAmount()));
            count.set(String.valueOf(details.getCount()));
        }
    }


}
