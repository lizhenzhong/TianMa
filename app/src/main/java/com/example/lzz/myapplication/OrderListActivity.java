package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lzz.myapplication.databinding.ActivityOrderListBinding;

import java.util.List;

public class OrderListActivity extends BaseActivity {
    private OrderListAdp mAdp;
    private OrderListVm mViewModel;
    private ActivityOrderListBinding binding;

    public static void skipToOrderListAct(Context context) {
        Intent i = new Intent(context, OrderListActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new OrderListVm();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list);
        binding.setViewModel(mViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 2), false);

        binding.rcOrders.addItemDecoration(itemDecoration);
        binding.rcOrders.setLayoutManager(linearLayoutManager);

        setData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setData();
    }

    private void setData() {
        List<OrderItemVm> orderItemVms = mViewModel.queryOrders();
        if (mAdp == null) {
            mAdp = new OrderListAdp(this, orderItemVms);
            binding.rcOrders.setAdapter(mAdp);
        }
    }

}
