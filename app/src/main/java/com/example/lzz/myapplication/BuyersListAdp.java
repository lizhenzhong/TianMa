package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemBuyersListBinding;

import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class BuyersListAdp extends RecyclerView.Adapter<BuyersListAdp.Holder> {
    private LayoutInflater inflater;
    private List<BuyersItemVm> buyersItemVms;

    public BuyersListAdp(Context context, List<BuyersItemVm> buyersItemVms) {
        this.buyersItemVms = buyersItemVms;
        inflater = LayoutInflater.from(context);
    }

    public void refresh(List<BuyersItemVm> itemVms) {
        if (itemVms != null) {
            buyersItemVms.clear();
            buyersItemVms.addAll(itemVms);
        }
        notifyDataSetChanged();
    }

    @Override
    public BuyersListAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBuyersListBinding binding = ItemBuyersListBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(BuyersListAdp.Holder holder, int position) {
        holder.bind(buyersItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return buyersItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemBuyersListBinding binding;

        public Holder(ItemBuyersListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BuyersItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
