package com.example.lzz.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/7/14.
 */

public class BuyersListVm {
    private BuyerDao buyerDao;

    public BuyersListVm() {
        buyerDao = LApplication.it().getDaoSession().getBuyerDao();
    }

    public List<BuyersItemVm> queryBuyers() {
        List<BuyersItemVm> buyersItemVms = new ArrayList<>();
        List<Buyer> buyers = buyerDao.queryBuilder()
                .list();

        if (buyers != null) {
            for (Buyer buyer : buyers) {
                BuyersItemVm item = new BuyersItemVm(buyer);
                buyersItemVms.add(item);
            }
        }
        return buyersItemVms;
    }


}
