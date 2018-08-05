package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lzz.myapplication.databinding.ActivityOrderDetailsBinding;

import java.util.List;

public class OrderDetailsActivity extends BaseActivity {
    private static final String EXTRA_ORDER_ID = "order_id";

    private OrderDetailsAdp mAdp;
    private OrderDetailsVm mViewModel;
    private ActivityOrderDetailsBinding binding;
    private long orderId;

    public static void skipToOrderDetailsAct(Context context, long orderId) {
        Intent i = new Intent(context, OrderDetailsActivity.class);
        i.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        orderId = intent.getLongExtra(EXTRA_ORDER_ID, -1);

        mViewModel = new OrderDetailsVm();
        mViewModel.setOrderInfo(orderId);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        binding.setViewModel(mViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 2), false);

        binding.rcOrderDetails.addItemDecoration(itemDecoration);
        binding.rcOrderDetails.setLayoutManager(linearLayoutManager);

        setData();
    }

    private void setData() {
        List<OrderDetailsItemVm> orderItemVms = mViewModel.queryOrderDetails(orderId);
        if (mAdp == null) {
            mAdp = new OrderDetailsAdp(this, orderItemVms);
            binding.rcOrderDetails.setAdapter(mAdp);
        }
    }

}
