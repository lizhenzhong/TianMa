package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemBuyersListBinding;
import com.example.lzz.myapplication.databinding.ItemOrderListBinding;

import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class OrderListAdp extends RecyclerView.Adapter<OrderListAdp.Holder> {
    private LayoutInflater inflater;
    private List<OrderItemVm> orderItemVms;

    public OrderListAdp(Context context, List<OrderItemVm> orderItemVms) {
        this.orderItemVms = orderItemVms;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public OrderListAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderListBinding binding = ItemOrderListBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(OrderListAdp.Holder holder, int position) {
        holder.bind(orderItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return orderItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemOrderListBinding binding;

        public Holder(ItemOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OrderItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
