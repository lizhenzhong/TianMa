package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Created by lzz on 2018/7/14.
 */

public class CarItemVm {
    public final ObservableField<Uri> photo = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> count = new ObservableField<>("");
    private CarManage.Car car;

    public final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!TextUtils.isEmpty(charSequence)) {
                long count = Long.parseLong(charSequence.toString());
                count = count > 0 ? count : 0;
                car.count = count;
            } else {
                car.count = 0;
            }
            setName(String.valueOf(CarManage.getInstance().getAmount(car)));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            CarManage.getInstance().onAmountChanged();
        }
    };

    public CarItemVm(CarManage.Car car) {
        this.car = car;
        photo.set(Uri.parse(car.product.getPhoto()));
        setName(String.valueOf(CarManage.getInstance().getAmount(car)));
        count.set(String.valueOf(car.count));
    }

    private void setName(String amount) {
        name.set(LApplication.it().getString(R.string.car_product_price, car.product.getName(), amount));
    }

}
