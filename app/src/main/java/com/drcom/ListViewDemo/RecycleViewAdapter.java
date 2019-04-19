package com.drcom.ListViewDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Don on 2019/4/18.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.Viewholder> {

    private List<ServiceKindInfo> mList;

    private  RecycleItemClickListener recycleItemClickListener;
    private LayoutInflater inflater;
    private Context mContext;


    public RecycleViewAdapter(Context context, List<ServiceKindInfo> arrayList) {
        inflater = LayoutInflater.from(context);
        this.mContext= context;
        this.mList = arrayList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.recyclerlist_cell,null);
        Viewholder viewholder = new Viewholder(v);
        return  viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.textView.setText(mList.get(position).getName());
        holder.imageView.setImageResource(R.drawable.ic_arrow_right);

        if(mList.get(position).isSelected()) {
            holder.imageView.setImageResource(R.drawable.detail_interaction_bar_favorite_pressed);
        }else{
            holder.imageView.setImageResource(R.drawable.detail_interaction_bar_favorite);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleItemClickListener.onItemClick(v, position);
            }
        });
    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }



    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setOnItemClickListener(RecycleItemClickListener onItemClickListener){
        recycleItemClickListener= onItemClickListener;
    }
    public interface  RecycleItemClickListener {
        void onItemClick(View view,int position);
    }



    class Viewholder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.Itemtitle);
            imageView = itemView.findViewById(R.id.ItemImage);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recycleItemClickListener!=null){
//                    recycleItemClickListener.onItemClick(v,getPosition());
//                }
//            }
//        });

        }

    }
}


