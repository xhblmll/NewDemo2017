package com.example.gsondemo;

import java.util.ArrayList;

import com.example.gsondemo.bean.Tag;
import com.jike.gsondemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private ArrayList<Tag> list;
	private Context context;
    public MyAdapter(Context context,ArrayList<Tag> list){
       this.context=context;
       this.list=list;
    }
    //刷新list列表中的adapter;
    public void refreshData(ArrayList<Tag> list) {
           this.list=list;
		   notifyDataSetChanged();
		}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
		
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolder vh;
		if(convertView==null){
			vh=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.item, null);
			vh.tv_count=(TextView) convertView.findViewById(R.id.tv_count);
			vh.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(vh);
			
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		vh.tv_count.setText(list.get(position).getCount());
		vh.tv_title.setText(list.get(position).getTitle());
		return convertView;
	}
	static final class ViewHolder{
		TextView tv_count;
		TextView tv_title;
	}
	
	

}
