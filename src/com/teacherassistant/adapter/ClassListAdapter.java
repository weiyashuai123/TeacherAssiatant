package com.teacherassistant.adapter;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClassListAdapter extends ArrayAdapter<Classes> {

	private int resourceId ;
	public ClassListAdapter(Context context, int textViewResourceId, List<Classes> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Classes cla = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView tv_name = (TextView) view.findViewById(R.id.classlist_name_join);
		TextView tv_peoplenumber = (TextView) view.findViewById(R.id.classlist_peopleNumber_join);
		TextView tv_teacher = (TextView) view.findViewById(R.id.classlist_teacher_join);
		
		tv_name.setText(cla.getName());
		tv_peoplenumber.setText(cla.getPeoplenumber());
		tv_teacher.setText(cla.getTeacher());
		return view;
	}
	

}
