package com.example.admin.androidweather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class FunctionAdapter extends RecyclerView.Adapter <FunctionAdapter.MyHolder> {

    private List<Function> functionList;

    static class MyHolder extends RecyclerView.ViewHolder{
        View functionView;
        Button functionName;

        public MyHolder(View view){
            super(view);
            functionName = (Button) view.findViewById(R.id.function_name);
        }
    }

    public FunctionAdapter(List<Function> list){

        this.functionList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.function_item,parent ,false);
        final MyHolder holder = new MyHolder(view);
        /*
        * 点击事件
        * */
        holder.functionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(),ThirdActicity.class);
               /*
               * 没有必要用recyclerView写按键布局
               * */
            }
        });




        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder,int position){
        Function item = functionList.get(position);
        holder.functionName.setText(item.getName());
    }

    @Override
    public int getItemCount(){
        return functionList.size();
    }


}
