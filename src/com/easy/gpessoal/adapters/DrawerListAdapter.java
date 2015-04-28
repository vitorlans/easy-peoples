package com.easy.gpessoal.adapters;

import java.util.List;

import com.easy.gpessoal.R;
import com.easy.gpessoal.models.Drawer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter {
	
	Context mContext;
	LayoutInflater mInflater;
	List<Drawer> mDrawerList;
	
	public DrawerListAdapter(Context context, List<Drawer> DrawerItens){
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mDrawerList = DrawerItens;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDrawerList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDrawerList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class ViewHolder{
		
		ImageView ivIcon;
		TextView tvLabel;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
				
		 final ViewHolder holder;
	      final View rootView;
	      if(convertView==null)
	        {
	        holder = new ViewHolder();
	        rootView = mInflater.inflate(R.layout.drawer_row, parent, false);
	        holder.ivIcon=(ImageView)rootView.findViewById(R.id.iv_icon);

	        holder.tvLabel=(TextView)rootView.findViewById(R.id.tv_label);
	        rootView.setTag(holder);
	        }
	      else
	        {
	        rootView=convertView;
	        holder=(ViewHolder)rootView.getTag();
	        }
	      
	      final Drawer d = mDrawerList.get(position);
	      
	      holder.ivIcon.setImageResource(d.getImagem());
	      holder.tvLabel.setText(d.getLabel());
		
	      
		return rootView;
	}

}
