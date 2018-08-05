package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lzz.myapplication.databinding.ActivityCarBinding;

public class CarActivity extends BaseActivity implements CarManage.OnAmountChangeListener {
    private CarAdp carAdp;
    private CarVm carVm;

    public static void skipToProductListAct(Context context) {
        Intent i = new Intent(context, CarActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        carVm = new CarVm();
        ActivityCarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_car);
        binding.setViewModel(carVm);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 2), false);

        binding.rcCar.addItemDecoration(itemDecoration);
        binding.rcCar.setLayoutManager(linearLayoutManager);

        carAdp = new CarAdp(this, carVm.getCarItems());
        binding.rcCar.setAdapter(carAdp);

        CarManage.getInstance().setListener(this);
    }

    @Override
    protected void onDestroy() {
        CarManage.getInstance().resize();
        CarManage.getInstance().setListener(null);
        CarManage.getInstance().clearOrder();
        super.onDestroy();
    }

    @Override
    public void onChange() {
        if (carVm != null) {
            carVm.onAllAmountChanged();
        }
    }

    public void onClickCreate(View view) {
        if (CarManage.getInstance().isEmpty()) {
            Toast.makeText(this, "请选择购买的商品", Toast.LENGTH_SHORT).show();
            return;
        }
        CarManage.getInstance().createOrder();
        SelectBuyerActivity.skipToSelectBuyerAct(this);
    }
}
