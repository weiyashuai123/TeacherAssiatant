package com.teacherassistant.adapter;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class JobListAdapter extends ArrayAdapter<Job>{

	private int resourceId ;
	public JobListAdapter(Context context, int textViewResourceId, List<Job> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Job job = getItem(position);
		
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView tv_course = (TextView) view.findViewById(R.id.txt_course_joblist);
		TextView tv_content = (TextView) view.findViewById(R.id.txt_content_joblist);
		TextView tv_teacher = (TextView) view.findViewById(R.id.txt_teacher_joblist);
		TextView tv_date = (TextView) view.findViewById(R.id.txt_date_joblist);

		tv_course.setText(job.getCourseName());
		tv_content.setText(job.getJobContent());
		tv_teacher.setText(job.getTeacher());
		tv_date.setText(job.getCreatedAt());
		
		return view;
	}
	

}
