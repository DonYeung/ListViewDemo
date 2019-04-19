package com.drcom.ListViewDemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ThridActivity extends BaseActivity implements MyItemClickListener{
    private RecyclerView Rv;
    private ArrayList<HashMap<String,Object>> listItem;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.third_activity);
        initData();
        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.third_activity;
    }

    private void initView() {
        setToolBarTitle("asdasd");
        setNaviBack(false);
        isShowrightbtn(true);
//        mToolBar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbarTitle = (TextView)findViewById(R.id.toolbar_title);
//        //将Toolbar显示到界面
//        setSupportActionBar(mToolBar);
//
//
//        mToolBar.setTitle("ThridActivity");
////        mToolbarTitle.setText("ThridActivity");
//        mToolBar.setNavigationIcon(R.drawable.nav_menu);
//        //扩展menu
//        mToolBar.inflateMenu(R.menu.menu_toolbar);
//        mToolBar.getMenu().findItem(R.id.right_icon).setVisible(true);


        Rv = findViewById(R.id.my_recycler_view);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);
        Rv.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));//用类设置分割线
//Rv.addItemDecoration(new DividerItemDecoration(this, R.drawable.list_divider)); //用已有图片设置分割线

        //为ListView绑定适配器
        recyclerViewAdapter = new RecyclerViewAdapter(this,listItem);
        recyclerViewAdapter.setOnItemClickListener(this);
        Rv.setAdapter(recyclerViewAdapter);

    }


    private void initData() {
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            map.put("ItemImage",R.mipmap.ic_launcher);
            listItem.add(map);
        }

    }



    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第"+postion+"行");
        Intent intent =new Intent();
        intent.setClass(ThridActivity.this,VLayoutActivityDemo.class);
        startActivity(intent);
        Toast.makeText(this, (String)listItem.get(postion).get("ItemText"), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.right_icon:
                Toast.makeText(this,"right_icon",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    private long prePressBackTime;  //上一次点击返回键时间
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - prePressBackTime < 2000){//在两秒内，退出
                return super.dispatchKeyEvent(event);
            }
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            prePressBackTime = System.currentTimeMillis();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


}
