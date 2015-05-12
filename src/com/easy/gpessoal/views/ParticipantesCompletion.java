package com.easy.gpessoal.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easy.gpessoal.R;
import com.easy.gpessoal.models.Usuarios;
import com.tokenautocomplete.TokenCompleteTextView;

public class ParticipantesCompletion extends TokenCompleteTextView{
	 public ParticipantesCompletion(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    @Override
	    protected View getViewForObject(Object object) {
	        Usuarios p = (Usuarios)object;

	        LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	        LinearLayout view = (LinearLayout)l.inflate(R.layout.partic_token, (ViewGroup)ParticipantesCompletion.this.getParent(), false);
	        ((TextView)view.findViewById(R.id.name)).setText(p.getEmail());

	        return view;
	    }

	    @Override
	    protected Object defaultObject(String completionText) {
	        //Stupid simple example of guessing if we have an email or not
	        int index = completionText.indexOf('@');
	        if (index == -1) {
	            return new Usuarios(completionText, completionText.replace(" ", "") + "@gmail.com");
	        } else {
	            return new Usuarios(completionText.substring(0, index), completionText);
	        }
	    }

}
