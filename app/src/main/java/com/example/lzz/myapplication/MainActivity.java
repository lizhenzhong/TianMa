package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lzz.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements RoundMenu.OnMenuClickListener {

    public static void skipToMainAct(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setUser(new MainVm());
        binding.roundMenuMain.setOnMenuClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_car:
                CarActivity.skipToCarAct(this);
                break;
            case R.id.menu_add_product:
                AddProductActivity.skipToAddProductAct(this);
                break;
            case R.id.menu_add_buyer:
                BuyersListActivity.skipToBuyersListAct(this);
                break;
            case R.id.menu_order_list:
                OrderListActivity.skipToOrderListAct(this);
                break;
            case R.id.menu_clear_car:
                CarManage.getInstance().clearCar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuClick(int position) {
        if (position > 0) {
            ProductListActivity.skipToProductListAct(this, position);
        } else if (position == -1) {
            CarActivity.skipToCarAct(this);
        }
    }
}
