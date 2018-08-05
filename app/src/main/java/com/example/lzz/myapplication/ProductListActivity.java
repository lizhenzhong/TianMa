package com.example.lzz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lzz.myapplication.databinding.ActivityMainBinding;
import com.example.lzz.myapplication.databinding.ActivityProductListBinding;

import java.util.List;

public class ProductListActivity extends BaseActivity {
    public static final String EXTRA_TYPE = "type";

    private ProductListVm mViewModel;
    private ProductListAdp mAdp;

    public static void skipToProductListAct(Context context, int type) {
        Intent i = new Intent(context, ProductListActivity.class);
        i.putExtra(EXTRA_TYPE, type);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ProductListVm();
        ActivityProductListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list);
        binding.setViewModel(mViewModel);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(this, 2), false);

        binding.rcProducts.addItemDecoration(itemDecoration);
        binding.rcProducts.setLayoutManager(gridLayoutManager);

        Intent i = getIntent();
        int type = i.getIntExtra(EXTRA_TYPE, -1);
        setTitles(type);

        List<ProductItemVm> productItemVms = mViewModel.queryProduct(type);
        mAdp = new ProductListAdp(this, productItemVms);
        binding.rcProducts.setAdapter(mAdp);
    }

    private void setTitles(int type) {
        switch (type) {
            case ProductType.TEA_KNIFE:
                setTitle("茶刀");
                break;
            case ProductType.POT_PEN:
                setTitle("养壶笔");
                break;
            case ProductType.TEA_CLIPS:
                setTitle("茶夹");
                break;
            case ProductType.TEA_PADS:
                setTitle("茶垫");
                break;
            case ProductType.OTHERS:
                setTitle("其他配件");
                break;
            default:
                break;
        }
    }
}
