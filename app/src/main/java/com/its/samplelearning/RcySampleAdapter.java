package com.its.samplelearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RcySampleAdapter extends RecyclerView.Adapter<RcySampleAdapter.MyViewHolder> {

    private ArrayList<UserPosts> data = new ArrayList<>();

    RcySampleAdapter(ArrayList<UserPosts> tempData) {
        this.data = tempData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rcy_sample_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserPosts myData = data.get(position);

        holder.mTxtView.setText(myData.getId());
        holder.mTxtView2.setText(myData.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTxtView;
        public TextView mTxtView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTxtView = itemView.findViewById(R.id.txt_view);
            this.mTxtView2 = itemView.findViewById(R.id.txt_view2);
        }
    }
}
