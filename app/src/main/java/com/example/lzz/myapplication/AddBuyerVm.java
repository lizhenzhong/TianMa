package com.example.lzz.myapplication;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

import static com.example.lzz.myapplication.ProductType.TEA_KNIFE;

/**
 * Created by lzz on 2018/7/14.
 */

public class AddBuyerVm {
    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> phoneNumber = new ObservableField<>("");
    public final ObservableField<String> address = new ObservableField<>("");
    public final ObservableField<Boolean> isNew = new ObservableField<>(false);
    private BuyerDao buyerDao;
    private long id;

    public AddBuyerVm(long id) {
        this.id = id;
        buyerDao = LApplication.it().getDaoSession().getBuyerDao();
        setBuyer(id);
    }

    public void deleteBuyer() {
        buyerDao.deleteByKey(id);
    }

    private Buyer queryBuyer(long id) {
        List<Buyer> buyers = buyerDao.queryBuilder()
                .where(BuyerDao.Properties.Id.eq(id))
                .list();
        return buyers.isEmpty() ? null : buyers.get(0);
    }

    private void setBuyer(long id) {
        isNew.set(id <= 0);
        Buyer buyer = queryBuyer(id);
        if (buyer != null) {
            name.set(buyer.getName());
            phoneNumber.set(buyer.getPhoneNumber());
            address.set(buyer.getAddress());
        }
    }

    public boolean commit() {
        if (TextUtils.isEmpty(name.get())) {
            return false;
        }
        if (TextUtils.isEmpty(phoneNumber.get())) {
            return false;
        }
        if (TextUtils.isEmpty(address.get())) {
            return false;
        }

        Buyer buyer = isNew.get() ? new Buyer() : queryBuyer(id);
        buyer.setName(name.get());
        buyer.setPhoneNumber(phoneNumber.get());
        buyer.setAddress(address.get());

        if (isNew.get()) {
            buyerDao.insert(buyer);
        } else {
            buyerDao.update(buyer);
        }
        return true;
    }
}
