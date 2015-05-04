package com.easy.gpessoal.adapters;

 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
 
import com.easy.gpessoal.R; 
import com.easy.gpessoal.models.Compromissos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CompromissosAdapter extends BaseExpandableListAdapter {
 
    private Activity context;
    private Map<String, List<Compromissos>> compromissosCollections;
    private List<String> groupList;
 
    public CompromissosAdapter(Activity context, List<String> gList,
            Map<String, List<Compromissos>> compCollections) {
        this.context = context;
        this.compromissosCollections = compCollections;
        this.groupList = gList;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return compromissosCollections.get(groupList.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
     
     
    @SuppressLint("InflateParams")
	public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
    	
        final Compromissos comp = (Compromissos) getChild(groupPosition, childPosition);
        
        LayoutInflater inflater = context.getLayoutInflater();
         
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
         
        TextView item = (TextView) convertView.findViewById(R.id.row_nome_tv);
        TextView item2 = (TextView) convertView.findViewById(R.id.row_data_tv);
        ImageView ivStatus =(ImageView) convertView.findViewById(R.id.row_st_iv);
        item.setText(comp.getTitulo());
        item2.setText(comp.getDataInicio() +" - "+ comp.getDataFim());
        setStatus(comp, ivStatus);
        
        return convertView;
    }
 
	public int getChildrenCount(int groupPosition) {
        return compromissosCollections.get(groupList.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }
 
    public int getGroupCount() {
        return groupList.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @SuppressLint({ "InflateParams", "DefaultLocale" })
	public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String compNome = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        
        
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Date formattedDate = null;
		try {
			formattedDate = df.parse(compNome);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE - dd/MM/yyyy", Locale.getDefault());
    	compNome = sdf.format(formattedDate);
    	compNome=compNome.toUpperCase();
		
        item.setTypeface(null, Typeface.BOLD);
        item.setText(compNome);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
    private void setStatus(Compromissos comp, ImageView ivStatus) {

    	Calendar c = Calendar.getInstance();
        Date dfim = null;
        String sfim = comp.getDataFim().substring(0,10);
        
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		try {
			
			dfim = sdf.parse(sfim);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Date dnow = c.getTime();
        
        String snow = sdf.format(dnow);
		
        
        if(dfim.before(dnow)){
        	ivStatus.setBackgroundColor(context.getResources().getColor(R.color.material_blue_grey_950));
        	
        }else {
        	ivStatus.setBackgroundColor(context.getResources().getColor(R.color.accent_material_light));

        }
        
        if(sfim.equals(snow)){
        	ivStatus.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        }
	}
}
