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
    private OnItemClickListener onItemClickListener;

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
    public void onBindViewHolder(final recvViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        if(this.onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
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

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
