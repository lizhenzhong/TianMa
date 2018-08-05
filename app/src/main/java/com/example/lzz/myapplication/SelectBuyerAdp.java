package com.example.lzz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lzz.myapplication.databinding.ItemBuyersListBinding;
import com.example.lzz.myapplication.databinding.ItemSelectBuyerBinding;

import java.util.List;
import java.util.Objects;

/**
 * Created by lzz on 2018/7/14.
 */

public class SelectBuyerAdp extends RecyclerView.Adapter<SelectBuyerAdp.Holder> {
    private LayoutInflater inflater;
    private List<SelectBuyerItemVm> buyersItemVms;

    public SelectBuyerAdp(Context context, List<SelectBuyerItemVm> buyersItemVms) {
        this.buyersItemVms = buyersItemVms;
        inflater = LayoutInflater.from(context);
    }

    public void refresh(List<SelectBuyerItemVm> itemVms) {
        if (itemVms != null) {
            buyersItemVms.clear();
            buyersItemVms.addAll(itemVms);
            notifyDataSetChanged();
        }
    }

    public void refresh(SelectBuyerItemVm itemVms) {
        if (itemVms != null) {
            for (SelectBuyerItemVm vm : buyersItemVms) {
                if (Objects.equals(vm.getBuyer().getId(), itemVms.getBuyer().getId())) {
                    vm.isSelected.set(!vm.isSelected.get());
                    CarManage.getInstance().setCurrBuyer(vm.isSelected.get() ? vm.getBuyer() : null);
                } else {
                    vm.isSelected.set(false);
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public SelectBuyerAdp.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSelectBuyerBinding binding = ItemSelectBuyerBinding.inflate(inflater, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(SelectBuyerAdp.Holder holder, int position) {
        holder.bind(buyersItemVms.get(position));
    }

    @Override
    public int getItemCount() {
        return buyersItemVms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ItemSelectBuyerBinding binding;

        public Holder(ItemSelectBuyerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SelectBuyerItemVm vm) {
            binding.setViewModel(vm);
        }
    }
}
