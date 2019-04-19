package com.drcom.ListViewDemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private  Toolbar mToolBar;
    private TextView mToolbarTitle;
    private boolean isShowrightbtn;
    private View.OnClickListener mBackOnclickListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    private void init() {
        mToolBar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        if (mToolBar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolBar);
            //扩展menu
            mToolBar.inflateMenu(R.menu.menu_toolbar);
            //添加监听
            mToolBar.setOnMenuItemClickListener(this);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }


    /**
     * 设置头部标题
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if(mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else{
            mToolBar.setTitle(title);
            setSupportActionBar(mToolBar);
        }
    }
    /**
     * 设置显示返回按钮
     */
    protected void setNaviBack(boolean visible) {
        if (visible) {
            mToolBar.setNavigationIcon(R.drawable.nav_menu);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });//设置返回按钮
        }
    }



    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public boolean isShowrightbtn(boolean isShowright){
            isShowrightbtn=isShowright;
        return isShowrightbtn;
    }



    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        if (isShowrightbtn){
            mToolBar.getMenu().findItem(R.id.right_icon).setVisible(true);
        }else{
            mToolBar.getMenu().findItem(R.id.right_icon).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }




    /**
     * 截取返回按钮事件
     */
    @Override
    public boolean onKeyDown(int keyCoder, KeyEvent event) {
        if (keyCoder == KeyEvent.KEYCODE_BACK) {
            if (mBackOnclickListener != null)
                mBackOnclickListener.onClick(mToolBar.getMenu().findItem(R.id.right_icon).getActionView());
            else
                finish();
            return false;
        }

        return false;
    }
}