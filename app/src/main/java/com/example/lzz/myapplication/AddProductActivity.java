package com.example.lzz.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.lzz.myapplication.databinding.ActivityAddProductBinding;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class AddProductActivity extends BaseActivity {
    private static final String EXTRA_PRODUCT_ID = "product_id";
    private static final int REQUEST_SELECT_IMAGE = 1;
    private AddProductVm mAddProductVm;
    private ActivityAddProductBinding mBinding;

    public static void skipToAddProductAct(Context context) {
        skipToAddProductAct(context, -1);
    }

    public static void skipToAddProductAct(Context context, long productId) {
        Intent i = new Intent(context, AddProductActivity.class);
        i.putExtra(EXTRA_PRODUCT_ID, productId);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);
        }

        Intent intent = getIntent();
        long productId = intent.getLongExtra(EXTRA_PRODUCT_ID, -1);
        mAddProductVm = new AddProductVm(productId);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_product);
        mBinding.setViewModel(mAddProductVm);

        mBinding.spProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mAddProductVm.setType(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickFinish(View view) {
        if (mAddProductVm.commit()) {
            Toast.makeText(this, mAddProductVm.isNew ? "添加成功" : "修改成功", Toast.LENGTH_SHORT).show();
            MainActivity.skipToMainAct(this);
            finish();
        } else {
            Toast.makeText(this, "请检查输入的内容是否正确", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSelectImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQUEST_SELECT_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                } else {
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            mAddProductVm.setPhoto(selectedImage);
        }
    }
}
