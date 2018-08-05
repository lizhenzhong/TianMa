package com.example.lzz.myapplication;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by lzz on 2018/7/14.
 */

public class DataBindingAdp {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(SimpleDraweeView view, Uri uri) {
        view.setImageURI(uri);
    }

    @BindingAdapter({"onTextWatch"})
    public static void setTextWatch(EditText view, TextWatcher watcher) {
        view.addTextChangedListener(watcher);
    }

    @BindingAdapter({"onItemSelect"})
    public static void setItemSelect(LinearLayout view, boolean isSelect) {
        view.setBackgroundColor(isSelect ? Color.YELLOW : Color.WHITE);
    }

    @BindingAdapter({"onLongPress"})
    public static void setLongPress(LinearLayout view, View.OnLongClickListener longClickListener) {
        view.setOnLongClickListener(longClickListener);
    }

    @BindingAdapter({"setSelection"})
    public static void setSelection(Spinner view, int position) {
        view.setSelection(position);
    }
}
