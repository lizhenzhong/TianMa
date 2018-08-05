package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemOrderDetailsBinding;
import com.example.lzz.myapplication.databinding.ItemOrderDetailsBinding;

import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderDetailsAdp extends RecyclerView.Adapter<OrderDetailsAdp.Holder> {
    private LayoutInflater inflater;
    private List<OrderDetailsItemVm> orderItemVms;

    public OrderDetailsAdp(Context context, List<OrderDetailsItemVm> orderItemVms) {
        this.orderItemVms = orderItemVms;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public OrderDetailsAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderDetailsBinding binding = ItemOrderDetailsBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(OrderDetailsAdp.Holder holder, int position) {
        holder.bind(orderItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return orderItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemOrderDetailsBinding binding;

        public Holder(ItemOrderDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OrderDetailsItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
