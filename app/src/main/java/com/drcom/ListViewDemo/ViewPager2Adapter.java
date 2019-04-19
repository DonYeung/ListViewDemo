package com.drcom.ListViewDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by Don on 2019/4/18.
 */
public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHoloder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;

    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};


    public ViewPager2Adapter (Context context, List<String> data, ViewPager2 viewPager2){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerlist_cell,parent,false);

        return new ViewHoloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoloder holder, int position) {

//        holder.myTextView.setText(mData.get(position));
        String animal = mData.get(position);
        holder.myTextView.setText(animal);

        holder.relativeLayout.setBackgroundResource(colorArray[position]);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHoloder extends RecyclerView.ViewHolder{
        private TextView myTextView;
        private RelativeLayout relativeLayout;


        public ViewHoloder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.Itemtitle);
            relativeLayout = itemView.findViewById(R.id.rl);

        }
    }
}
