package com.daf_gruppe.telaffuz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.daf_gruppe.telaffuz.R;
import com.daf_gruppe.telaffuz.dto.Word;

import java.util.ArrayList;


/**
 * Created by numanturkeri on 09/05/16.
 */
public class ListAdapter extends RecyclerView
        .Adapter<ListAdapter
        .DataObjectHolder> {
    static long contactType;
    private static String LOG_TAG = "ContactAdapter";
    private ArrayList<Word> mDataset;
    Context context;
    public ListAdapter(Context context, ArrayList<Word> myDataset) {
        mDataset = myDataset;
        this.context = context;
    }
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        ListAdapter.myClickListener = myClickListener;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_yaprak_tests, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).getText());

    }

    public void addItem(Word dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}