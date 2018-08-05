package com.example.lzz.myapplication;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class CarVm {
    public final ObservableField<String> allAmount = new ObservableField<>("");

    public CarVm() {
        onAllAmountChanged();
    }

    public List<CarItemVm> getCarItems() {
        List<CarItemVm> carItemVms = new ArrayList<>();
        List<CarManage.Car> cars = CarManage.getInstance().getCars();
        for (CarManage.Car car : cars) {
            CarItemVm carItemVm = new CarItemVm(car);
            carItemVms.add(carItemVm);
        }
        return carItemVms;
    }

    public void onAllAmountChanged() {
        allAmount.set(LApplication.it().getString(R.string.car_all_amount,
                String.valueOf(CarManage.getInstance().getAllAmount())));
    }
}
