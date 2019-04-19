package com.drcom.ListViewDemo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }//构造函数，传入数据


    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Title, Text;
        private ImageView ima;

        public Viewholder(View root) {
            super(root);
            Title = root.findViewById(R.id.Itemtitle);
            Text = root.findViewById(R.id.Itemtext);
            ima = root.findViewById(R.id.ItemImage);
            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null)
                                                myItemClickListener .onItemClick(v,getPosition());
                                        }

                                    }//监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }

        public ImageView getIma() {
            return ima;
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return new Viewholder(inflater.inflate(R.layout.recyclerlist_cell, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Viewholder vh = (Viewholder) viewHolder;
        vh.Title.setText((String) listItem.get(position).get("ItemTitle"));
        vh.Text.setText((String) listItem.get(position).get("ItemText"));
        vh.ima.setImageResource((Integer) listItem.get(position).get("ItemImage"));

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener = listener;
    }//绑定MainActivity传进来的点击监听器

}
