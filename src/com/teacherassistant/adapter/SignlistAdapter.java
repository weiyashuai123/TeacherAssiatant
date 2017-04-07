package com.teacherassistant.adapter;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Answer;
import com.teacherassistant.DBmodel.SignRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SignlistAdapter extends ArrayAdapter<SignRecord>{

	private int resourceId ;
	public SignlistAdapter(Context context, int textViewResourceId, List<SignRecord> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SignRecord mAnswer = getItem(position);
		
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView tv_answerer= (TextView) view.findViewById(R.id.txt_classname_recordlist);
		TextView tv_context = (TextView) view.findViewById(R.id.txt_simple_recordlist);
		TextView tv_date = (TextView) view.findViewById(R.id.txt_date_recordlist);


		tv_answerer.setText(mAnswer.getClassname());
		tv_context.setText(mAnswer.getSimple());
		tv_date.setText(mAnswer.getCreatedAt());
		
		
		return view;
	}
}
