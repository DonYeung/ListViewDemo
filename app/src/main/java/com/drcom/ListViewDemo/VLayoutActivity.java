package com.drcom.ListViewDemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class VLayoutActivity extends BaseActivity implements MyItemClickListener{
    private RecyclerView recyclerView;
    private VLayoutAdapter myAdapter;
    private ArrayList<HashMap<String, Object>> listItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vlayout_activity;
    }


    private void init(){
        setNaviBack(false);
        setToolBarTitle("VLayoutActivity Demo");
        isShowrightbtn(false);
    }
    private void initData() {

        /**
         * 步骤1：创建RecyclerView & VirtualLayoutManager 对象并进行绑定
         * */
        recyclerView = findViewById(R.id.my_recycler_view);
        // 创建RecyclerView对象

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView

        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);


        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);

        }
        /**
         * 步骤4:根据数据列表,创建对应的LayoutHelper
         * */

        // 为了展示效果,此处将上面介绍的所有布局都显示出来

        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(4);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔


        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        myAdapter  = new VLayoutAdapter(this, linearLayoutHelper, 4, listItem) {
            // 参数2:绑定绑定对应的LayoutHelper
            // 参数3:传入该布局需要显示的数据个数
            // 参数4:传入需要绑定的数据

            // 通过重写onBindViewHolder()设置更丰富的布局效果
//            @Override
//            public void onBindViewHolder(VLayoutViewHolder holder, int position) {
//                super.onBindViewHolder(holder, position);
//                // 为了展示效果,将布局的第一个数据设置为linearLayout
//                if (position == 0) {
//                    holder.Text.setText("Linear");
//                }
//
//                //为了展示效果,将布局里不同位置的Item进行背景颜色设置
//                if (position < 2) {
//                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
//                } else if (position % 2 == 0) {
//                    holder.itemView.setBackgroundColor(0xaa22ff22);
//                } else {
//                    holder.itemView.setBackgroundColor(0xccff22ff);
//                }
//
//
//            }
        };

        myAdapter.setOnItemClickListener(this);// 设置每个Item的点击事件

        /**
         *步骤5:将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
         **/
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）

        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
//        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
//        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
//        delegateAdapter.setAdapters(myAdapter);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    /**
     *步骤7:实现Item点击事件
     **/
    // 点击事件的回调函数
    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第"+postion+"行");
        Toast.makeText(this, (String) listItem.get(postion).get("ItemTitle"), Toast.LENGTH_SHORT).show();
    }
}
