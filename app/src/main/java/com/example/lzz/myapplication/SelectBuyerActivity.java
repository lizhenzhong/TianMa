package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lzz.myapplication.databinding.ActivitySelectBuyerBinding;

import java.util.List;

public class SelectBuyerActivity extends BaseActivity {
    private SelectBuyerAdp mAdp;
    private SelectBuyerVm mViewModel;
    private ActivitySelectBuyerBinding mBinding;

    public static void skipToSelectBuyerAct(Context context) {
        Intent i = new Intent(context, SelectBuyerActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new SelectBuyerVm();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_buyer);
        mBinding.setViewModel(mViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 2), false);

        mBinding.rcBuyers.addItemDecoration(itemDecoration);
        mBinding.rcBuyers.setLayoutManager(linearLayoutManager);

        setData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setData();
    }

    private void setData() {
        List<SelectBuyerItemVm> buyersItemVms = mViewModel.queryBuyers();
        if (mAdp == null) {
            mAdp = new SelectBuyerAdp(this, buyersItemVms);
            mBinding.rcBuyers.setAdapter(mAdp);
        } else {
            mAdp.refresh(buyersItemVms);
        }
    }

    public void refresh(SelectBuyerItemVm itemVm) {
        if (mAdp != null) {
            mAdp.refresh(itemVm);
        }
    }

    public void onClickCommit(View view) {
        if (CarManage.getInstance().getCurrBuyer() == null) {
            Toast.makeText(this, "请选择下单的客户", Toast.LENGTH_SHORT).show();
        } else {
            if (CarManage.getInstance().commitOrder()) {
                Toast.makeText(this, "下单成功", Toast.LENGTH_SHORT).show();
                CarManage.getInstance().clearCar();
                MainActivity.skipToMainAct(this);
            }
        }
    }
}
