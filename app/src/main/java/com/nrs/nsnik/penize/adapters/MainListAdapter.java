package com.nrs.nsnik.penize.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nrs.nsnik.penize.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder>{

    private Context mContext;
    private static final String TAG = MainListAdapter.class.getSimpleName();
    private List<String> mList;
    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_DATE = 1;


    public MainListAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }


    @Override
    public MainListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE_HEADER){
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_header_item,parent,false));
        }else {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_date_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(MainListAdapter.MyViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if(viewType==ITEM_TYPE_HEADER){
            holder.mItemValue.setText(mList.get(position));
        }else {
           holder.mItemValue.setText(mList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_TYPE_HEADER;
        }else {
            return ITEM_TYPE_DATE;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void modifySingleValues(String val,int position){
        mList.set(position,val);
        notifyItemChanged(position);
    }

    public void modifyAllValues(List<String> list){
        mList  = list;
        notifyDataSetChanged();
    }

    public void removeValue(int position){
        mList.remove(position);
        notifyItemChanged(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.singleItemValue) TextView mItemValue;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, getAdapterPosition()+"");
                }
            });
        }
    }
}
