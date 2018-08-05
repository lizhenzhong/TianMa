package com.example.lzz.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by lzz on 2018/7/14.
 */

class CarManage {
    private static CarManage sInstance = new CarManage();
    private List<Car> mCars;
    private Order mCurrOrder;
    private Buyer mCurrBuyer;

    private OnAmountChangeListener listener;

    synchronized static CarManage getInstance() {
        return sInstance;
    }

    void createOrder() {
        synchronized (CarManage.class) {
            if (mCurrOrder == null) {
                mCurrOrder = new Order();
            }
            mCurrOrder.setAmount(String.valueOf(getAllAmount()));
            mCurrOrder.setDate(new Date());
        }
    }

    boolean commitOrder() {
        synchronized (CarManage.class) {
            if (mCurrBuyer == null || mCurrOrder == null) {
                return false;
            }
            mCurrOrder.setBuyerId(mCurrBuyer.getId());
            mCurrOrder.setBuyerName(mCurrBuyer.getName());

            OrderDao orderDao = LApplication.it().getDaoSession().getOrderDao();
            long orderId = orderDao.insert(mCurrOrder);

            List<OrderDetails> details = new ArrayList<>();
            for (Car car : getCars()) {
                double amount = getAmount(car);
                if (amount > 0) {
                    OrderDetails detail = new OrderDetails();
                    detail.setAmount(String.valueOf(getAmount(car)));
                    detail.setOrderId(orderId);
                    detail.setCount(car.count);
                    detail.setProductId(car.product.getId());
                    detail.setProductName(car.product.getName());
                    details.add(detail);
                }
            }
            OrderDetailsDao orderDetailsDao = LApplication.it().getDaoSession().getOrderDetailsDao();
            orderDetailsDao.insertInTx(details);
            return true;
        }
    }

    void clearOrder() {
        mCurrOrder = null;
        mCurrBuyer = null;
    }

    void clearCar() {
        clearOrder();
        getCars().clear();
    }

    void setCurrBuyer(Buyer buyer) {
        mCurrBuyer = buyer;
    }

    Buyer getCurrBuyer() {
        return mCurrBuyer;
    }

    Order getOrder() {
        return mCurrOrder;
    }

    void addCar(Product product) {
        synchronized (CarManage.class) {
            if (product != null && !inCars(product)) {
                Car car = new Car();
                car.product = product;
                getCars().add(car);
            }
        }
    }

    boolean inCars(Product product) {
        boolean ret = false;
        for (Car car : getCars()) {
            if (Objects.equals(car.product.getId(), product.getId())) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    void resize() {
        synchronized (CarManage.class) {
            List<Car> cars = getCars();
            Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()) {
                Car car = iterator.next();
                if (car.count <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    List<Car> getCars() {
        if (mCars == null) {
            mCars = new ArrayList<>();
        }
        return mCars;
    }

    boolean isEmpty() {
        return getAllAmount() <= 0;
    }

    double getAmount(Car car) {
        double amount = 0f;
        try {
            amount = Double.parseDouble(car.product.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount * car.count;
    }

    double getAllAmount() {
        double amount = 0f;
        synchronized (CarManage.class) {
            List<Car> cars = getCars();
            for (Car car : cars) {
                amount += getAmount(car);
            }
        }
        return amount;
    }

    void onAmountChanged() {
        if (listener != null) {
            listener.onChange();
        }
    }

    void setListener(OnAmountChangeListener listener) {
        this.listener = listener;
    }

    static class Car {
        Product product;
        long count;
    }

    interface OnAmountChangeListener {
        void onChange();
    }
}
