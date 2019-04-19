package com.drcom.ListViewDemo;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.MenuItem;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setToolBarTitle("bbbb");
        setNaviBack(true);
        isShowrightbtn(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.secondlayout;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.right_icon:
                Toast.makeText(this,"seconde right_icon",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
