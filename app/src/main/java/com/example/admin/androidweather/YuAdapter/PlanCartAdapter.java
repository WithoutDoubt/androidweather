package com.example.admin.androidweather.YuAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.androidweather.R;
import com.example.admin.androidweather.gson.ComponentGson;

import java.util.List;

public class PlanCartAdapter extends BaseAdapter {
    private List<ComponentGson> componentList;

    private CheckInterface checkInterface;
    private Context context;

    public PlanCartAdapter(Context context){
        this.context = context;
    }

    public void setComponentList(List<ComponentGson> componentList) {
    this.componentList = componentList ;
    notifyDataSetChanged();
}

   //单选接口
    public void setCheckInterface(CheckInterface checkInterface){
        this.checkInterface = checkInterface;
    }

    @Override
    public int getCount(){
        return componentList == null ? 0 : componentList.size();
    }

    @Override
    public Object getItem(int position){
        return componentList.get(position);
    }

    @Override
    public long getItemId(int postiton) {
        return postiton;
    }

    //主逻辑
    @Override
    public View getView(final int position, View convertView , ViewGroup parent){
        final  ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_plan_layout,parent,false);
            holder  = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        final ComponentGson component = componentList.get(position);
        boolean choosed = component.isChoosed();
        if (choosed){
            holder.checkBox.setChecked(true);
            }else {
            holder.checkBox.setChecked(false);
        }
        holder.tvShowType.setText("构件类型： "+ component.getComponentTypeId());
        holder.tvShowCode.setText("构件编码： "+component.getComponentCode());

        holder.checkBox.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void  onClick(View v){
                    component.setChoosed(((CheckBox)v).isChecked());
                    checkInterface.checkGroup(position,((CheckBox)v).isChecked());
            }
        });

        return convertView;
    }

    //控件初始化
    class ViewHolder{
        TextView tvShowCode;
        TextView tvShowType;
        CheckBox checkBox;
        public ViewHolder(View itemView){
            checkBox = (CheckBox)itemView.findViewById(R.id.ck_chose);
            tvShowCode = (TextView)itemView.findViewById(R.id.show_code);
            tvShowType = (TextView)itemView.findViewById(R.id.show_type);
        }
    }

    //触发事件
    public interface CheckInterface{
        void checkGroup(int position,boolean isChecked);
    }



}
