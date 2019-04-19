package com.drcom.ListViewDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Don on 2019/3/22.
 * ${CLASSNAME}
 */
public class MyAdapter extends BaseAdapter {
    private List<String> mlist;
    private Context context;
    public MyAdapter(Context mcontext,List<String> list) {
        super();
        context=mcontext;
        mlist=list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item =mlist.get(position);
        viewholder viewholder;
        View view;
        if (convertView==null){
            viewholder=new viewholder();
            view =LayoutInflater.from(context).inflate(R.layout.activity_main,null);
            viewholder.tv_wenku=view.findViewById(R.id.age);
            view.setTag(viewholder);

        }else{
            view =convertView;
            viewholder = (MyAdapter.viewholder) view.getTag();
        }
        viewholder.tv_wenku.setText(item);
        return view;
    }


    //视图控件内部类
    public class viewholder{
        private TextView tv_wenku;
    }
}
