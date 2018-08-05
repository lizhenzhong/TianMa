package com.example.lzz.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lzz.myapplication.databinding.ActivityAddBuyerBinding;

public class AddBuyerActivity extends BaseActivity {
    public static final String EXTRA_BUYER_ID = "buyerId";
    public static final String EXTRA_IS_SELECT_BUYER = "isSelectBuyer";

    private AddBuyerVm mAddBuyerVm;
    private ActivityAddBuyerBinding mBinding;
    private AlertDialog mDialog;
    private boolean isSelectBuyer;

    public static void skipToAddBuyerAct(Context context) {
        skipToAddBuyerAct(context, -1);
    }

    public static void skipToAddBuyerAct(Context context, long id) {
        Intent i = new Intent(context, AddBuyerActivity.class);
        i.putExtra(EXTRA_BUYER_ID, id);
        context.startActivity(i);
    }

    public static void skipToAddBuyerAct(Context context, boolean isSelectBuyer) {
        Intent i = new Intent(context, AddBuyerActivity.class);
        i.putExtra(EXTRA_IS_SELECT_BUYER, isSelectBuyer);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        long buyerId = i.getLongExtra(EXTRA_BUYER_ID, -1);
        isSelectBuyer = i.getBooleanExtra(EXTRA_IS_SELECT_BUYER, false);

        mAddBuyerVm = new AddBuyerVm(buyerId);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_buyer);
        mBinding.setViewModel(mAddBuyerVm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mAddBuyerVm.isNew.get()) {
            MenuInflater inflater = new MenuInflater(this);
            inflater.inflate(R.menu.delete_buyer_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_delete_buyer:
                showDialog();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private void showDialog() {
        if (!isFinishing()) {
            if (mDialog == null) {
                mDialog = new AlertDialog.Builder(this)
                        .setTitle("删除客户")
                        .setMessage("确定是否要删除该客户")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAddBuyerVm.deleteBuyer();
                                BuyersListActivity.skipToBuyersListAct(AddBuyerActivity.this);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create();
            }
            mDialog.show();
        }
    }

    public void onClickFinish(View view) {
        if (mAddBuyerVm.commit()) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            if (isSelectBuyer) {
                SelectBuyerActivity.skipToSelectBuyerAct(this, mAddBuyerVm.phoneNumber.get());
            } else {
                BuyersListActivity.skipToBuyersListAct(this);
            }
            finish();
        } else {
            Toast.makeText(this, "请检查输入的内容是否正确", Toast.LENGTH_SHORT).show();
        }
    }

}
