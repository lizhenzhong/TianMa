package com.example.lzz.myapplication;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class SelectBuyerVm {
    private BuyerDao buyerDao;

    public SelectBuyerVm() {
        buyerDao = LApplication.it().getDaoSession().getBuyerDao();
    }

    public List<SelectBuyerItemVm> queryBuyers() {
        List<SelectBuyerItemVm> buyersItemVms = new ArrayList<>();
        List<Buyer> buyers = buyerDao.queryBuilder()
                .list();

        if (buyers != null) {
            for (Buyer buyer : buyers) {
                SelectBuyerItemVm item = new SelectBuyerItemVm(buyer);
                buyersItemVms.add(item);
            }
        }
        return buyersItemVms;
    }


}
