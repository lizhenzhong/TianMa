package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.text.TextUtils;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class SelectBuyerVm {
    private BuyerDao buyerDao;
    public final ObservableField<String> phoneNumber = new ObservableField<>("");

    public SelectBuyerVm() {
        buyerDao = LApplication.it().getDaoSession().getBuyerDao();
    }

    public List<SelectBuyerItemVm> queryBuyers() {
        final String phoneNumber = this.phoneNumber.get();
        List<SelectBuyerItemVm> buyersItemVms = new ArrayList<>();

        if (!TextUtils.isEmpty(phoneNumber)) {
            List<Buyer> buyers = buyerDao.queryBuilder()
                    .where(BuyerDao.Properties.PhoneNumber.eq(phoneNumber))
                    .list();

            if (buyers != null) {
                for (Buyer buyer : buyers) {
                    SelectBuyerItemVm item = new SelectBuyerItemVm(buyer);
                    buyersItemVms.add(item);
                }
            }
        }
        return buyersItemVms;
    }
}
