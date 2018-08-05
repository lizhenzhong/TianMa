package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lzz.myapplication.databinding.ActivityBuyersListBinding;

import java.util.List;

public class BuyersListActivity extends BaseActivity {
    private BuyersListAdp mAdp;
    private BuyersListVm mViewModel;
    private ActivityBuyersListBinding binding;

    public static void skipToBuyersListAct(Context context) {
        Intent i = new Intent(context, BuyersListActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new BuyersListVm();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buyers_list);
        binding.setViewModel(mViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 2), false);

        binding.rcBuyers.addItemDecoration(itemDecoration);
        binding.rcBuyers.setLayoutManager(linearLayoutManager);

        setData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setData();
    }

    private void setData() {
        List<BuyersItemVm> buyersItemVms = mViewModel.queryBuyers();
        if (mAdp == null) {
            mAdp = new BuyersListAdp(this, buyersItemVms);
            binding.rcBuyers.setAdapter(mAdp);
        } else {
            mAdp.refresh(buyersItemVms);
        }
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
                AddBuyerActivity.skipToAddBuyerAct(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
