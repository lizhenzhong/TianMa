package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

import static com.example.lzz.myapplication.ProductType.TEA_KNIFE;

/**
 * Created by lzz on 2018/7/14.
 */

public class AddProductVm {
    public final ObservableField<Uri> photo = new ObservableField<>();
    public final ObservableField<String> productName = new ObservableField<>("");
    public final ObservableField<String> productAmount = new ObservableField<>();
    public final ObservableField<Integer> position = new ObservableField<>(0);

    private int type = TEA_KNIFE;
    private ProductDao productDao;
    private Product product;
    public boolean isNew;

    public AddProductVm(long productId) {
        productDao = LApplication.it().getDaoSession().getProductDao();
        List<Product> products = productDao.queryBuilder()
                .where(ProductDao.Properties.Id.eq(productId))
                .list();
        if (!products.isEmpty()) {
            product = products.get(0);
            position.set(ProductType.getPosition(product.getType()));
            photo.set(Uri.parse(product.getPhoto()));
            productName.set(product.getName());
            productAmount.set(product.getAmount());
        }
        isNew = products.isEmpty();
    }

    public void setPhoto(Uri uri) {
        photo.set(uri);
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean commit() {
        if (TextUtils.isEmpty(photo.get().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(productName.get())) {
            return false;
        }
        if (TextUtils.isEmpty(productAmount.get())) {
            return false;
        }

        if (isNew) {
            product = new Product();
        }
        product.setType(type);
        product.setName(productName.get());
        product.setAmount(productAmount.get());
        product.setPhoto(photo.get().toString());

        if (isNew) {
            productDao.insert(product);
        } else {
            productDao.update(product);
        }
        return true;
    }
}
