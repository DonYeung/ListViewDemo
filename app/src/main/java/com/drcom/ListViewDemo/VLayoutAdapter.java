package com.drcom.ListViewDemo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class VLayoutAdapter extends DelegateAdapter.Adapter<VLayoutAdapter.VLayoutViewHolder> {
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    private ArrayList<HashMap<String, Object>> listItem;
    // 用于存放数据列表

    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    private MyItemClickListener myItemClickListener;
    // 用于设置Item点击事件

    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public VLayoutAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public VLayoutAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;

    }

    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    // 把ViewHolder绑定Item的布局
    @NonNull
    @Override
    public VLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.vlayoutitem, viewGroup, false));
    }

    // 绑定Item的数据
    @Override
    public void onBindViewHolder(@NonNull VLayoutViewHolder vLayoutViewHolder, int position) {
        vLayoutViewHolder.Text.setText((String) listItem.get(position).get("ItemTitle"));
        vLayoutViewHolder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
    }

    // 返回Item数目
    @Override
    public int getItemCount() {
        return count;
    }
    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(MyItemClickListener listener) {
        myItemClickListener = listener;
    }

    public class VLayoutViewHolder extends RecyclerView.ViewHolder {
        public TextView Text;
        public ImageView image;

        public VLayoutViewHolder(View root) {
            super(root);

            // 绑定视图
            Text = root.findViewById(R.id.Item);
            image = root.findViewById(R.id.Image);

            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null)
                                                myItemClickListener.onItemClick(v, getPosition());
                                        }

                                    }
                    //监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getText() {
            return Text;
        }
    }
}
