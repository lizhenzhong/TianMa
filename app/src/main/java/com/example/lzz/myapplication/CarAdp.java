package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemBuyersListBinding;
import com.example.lzz.myapplication.databinding.ItemCarBinding;

import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class CarAdp extends RecyclerView.Adapter<CarAdp.Holder> {
    private LayoutInflater inflater;
    private List<CarItemVm> carItemVms;

    public CarAdp(Context context, List<CarItemVm> carItemVms) {
        this.carItemVms = carItemVms;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CarAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCarBinding binding = ItemCarBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(CarAdp.Holder holder, int position) {
        holder.bind(carItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return carItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemCarBinding binding;

        public Holder(ItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CarItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
