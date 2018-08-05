package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import static com.example.lzz.myapplication.ProductType.TEA_KNIFE;

/**
 * Created by lzz on 2018/7/14.
 */

public class ProductItemVm {
    public final ObservableField<Uri> photo = new ObservableField<>();
    public final ObservableField<String> productName = new ObservableField<>("");
    public final ObservableField<String> productAmount = new ObservableField<>();
    private Product product;

    public ProductItemVm(Product product) {
        this.product = product;
        photo.set(Uri.parse(product.getPhoto()));
        productName.set(product.getName());
        productAmount.set(LApplication.it().getString(R.string.unit_price, product.getAmount()));
    }

    public void itemClick(View view) {
        CarManage.getInstance().addCar(product);
        CarActivity.skipToProductListAct(view.getContext());
    }

    public View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            AddProductActivity.skipToAddProductAct(view.getContext(), product.getId());
            return false;
        }
    };

}
