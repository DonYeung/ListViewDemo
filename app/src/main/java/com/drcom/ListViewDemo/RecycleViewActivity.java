package com.drcom.ListViewDemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Don on 2019/4/18.
 * ${CLASSNAME}
 */
public class RecycleViewActivity extends AppCompatActivity {
    private static final String TAG = "RecycleViewActivity";

    private RecycleViewAdapter recycleViewAdapter;
    private List<ServiceKindInfo> mServiceList = new ArrayList<ServiceKindInfo>();//服务类型数据
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        recyclerView =findViewById(R.id.my_recycler_view);
        for(int i=0;i<50;i++){
            ServiceKindInfo info = new ServiceKindInfo();
            info.setName("第"+i+"行");
            mServiceList.add(info);
        }


        recyclerView.setHasFixedSize(true);//避免每次绘制Item时，不再重新计算Item高度。


        recyclerView.setItemViewCacheSize(20);//根据需求修改RecyclerView默认的绘制缓存选项
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));//用类设置分割线
        recyclerView.setLayoutManager(layoutManager);


        recycleViewAdapter = new RecycleViewAdapter(this,mServiceList);

//        recycleViewAdapter.setHasStableIds(true);

        recyclerView.setAdapter(recycleViewAdapter);

        recycleViewAdapter.setOnItemClickListener(new RecycleViewAdapter.RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mServiceList.get(position).setSelected(true);
                recycleViewAdapter.notifyItemChanged(position);
                Log.i(TAG, "onItemClick: 点击第%1s行"+position);
            }
        });


    }
}
