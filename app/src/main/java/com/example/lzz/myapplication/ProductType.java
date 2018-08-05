package com.example.lzz.myapplication;

import android.text.TextUtils;

/**
 * Created by lzz on 2018/7/14.
 */

public class ProductType {
    public static final int TEA_KNIFE = 1;
    public static final int POT_PEN = 2;
    public static final int TEA_CLIPS = 3;
    public static final int TEA_PADS = 4;
    public static final int OTHERS = 5;

    /**
     * <item>茶刀</item>
     * <item>养壶笔</item>
     * <item>茶夹</item>
     * <item>茶垫</item>
     * <item>其他配件</item>
     *
     * @param type
     * @return
     */
    public static int getPosition(int type) {
        String[] productTypes = LApplication.it().getResources().getStringArray(R.array.product_type);
        int position = 0;
        switch (type) {
            case TEA_KNIFE:
                position = find(productTypes, "茶刀");
                break;
            case POT_PEN:
                position = find(productTypes, "养壶笔");
                break;
            case TEA_CLIPS:
                position = find(productTypes, "茶夹");
                break;
            case TEA_PADS:
                position = find(productTypes, "茶垫");
                break;
            case OTHERS:
                position = find(productTypes, "其他配件");
                break;
            default:
                break;
        }
        return position;
    }

    public static int find(String[] productTypes, String name) {
        int position = 0;
        for (int i = 0; i < productTypes.length; i++) {
            if (TextUtils.equals(productTypes[i], name)) {
                position = i;
                break;
            }
        }
        return position;
    }
}
