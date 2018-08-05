package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lzz.myapplication.databinding.ActivitySelectBuyerBinding;

import java.util.List;

public class SelectBuyerActivity extends BaseActivity {
    private static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    private SelectBuyerAdp mAdp;
    private SelectBuyerVm mViewModel;
    private ActivitySelectBuyerBinding mBinding;

    public static void skipToSelectBuyerAct(Context context) {
        Intent i = new Intent(context, SelectBuyerActivity.class);
        context.startActivity(i);
    }

    public static void skipToSelectBuyerAct(Context context, String phoneNumber) {
        Intent i = new Intent(context, SelectBuyerActivity.class);
        i.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
        context.startActivity(i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String phoneNumber = intent.getStringExtra(EXTRA_PHONE_NUMBER);
            if (!TextUtils.isEmpty(phoneNumber)) {
                mViewModel.phoneNumber.set(phoneNumber);
                onSearch(null);
            }
        }
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.add_buyers_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_add_buyer:
                AddBuyerActivity.skipToAddBuyerAct(this, true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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

    public void onSearch(View view) {
        List<SelectBuyerItemVm> buyersItemVms = mViewModel.queryBuyers();
        if (mAdp == null) {
            mAdp = new SelectBuyerAdp(this, buyersItemVms);
            mBinding.rcBuyers.setAdapter(mAdp);
        } else {
            mAdp.refresh(buyersItemVms);
        }
    }
}
