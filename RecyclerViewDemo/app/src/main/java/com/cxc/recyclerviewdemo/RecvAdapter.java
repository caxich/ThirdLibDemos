package com.cxc.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cxc on 2017/5/10.
 */

public class RecvAdapter extends RecyclerView.Adapter<RecvAdapter.recvViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public RecvAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public recvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recv_main,parent,false);
        recvViewHolder holder = new recvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(recvViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class recvViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public recvViewHolder(View view){
            super(view);
            tv = (TextView)view.findViewById(R.id.tv_recv_item);
        }
    }
}
