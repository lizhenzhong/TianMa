package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemProductListBinding;

import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class ProductListAdp extends RecyclerView.Adapter<ProductListAdp.Holder> {
    private LayoutInflater inflater;
    private List<ProductItemVm> productItemVms;

    public ProductListAdp(Context context, List<ProductItemVm> productItemVms) {
        this.productItemVms = productItemVms;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductListAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductListBinding binding = ItemProductListBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(ProductListAdp.Holder holder, int position) {
        holder.bind(productItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return productItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemProductListBinding binding;

        public Holder(ItemProductListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProductItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
