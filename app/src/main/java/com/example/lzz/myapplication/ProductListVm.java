package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.lzz.myapplication.ProductType.TEA_KNIFE;

/**
 * Created by lzz on 2018/7/14.
 */

public class ProductListVm {

    private ProductDao productDao;

    public ProductListVm() {
        productDao = LApplication.it().getDaoSession().getProductDao();
    }

    public List<ProductItemVm> queryProduct(int type) {
        List<ProductItemVm> productItemVms = new ArrayList<>();
        List<Product> products = productDao.queryBuilder()
                .where(ProductDao.Properties.Type.eq(type))
                .list();

        if (products != null) {
            for (Product p : products) {
                ProductItemVm item = new ProductItemVm(p);
                productItemVms.add(item);
            }
        }
        return productItemVms;
    }


}
