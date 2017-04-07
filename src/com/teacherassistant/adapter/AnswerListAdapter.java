package com.teacherassistant.adapter;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Answer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnswerListAdapter extends ArrayAdapter<Answer>{

	private int resourceId ;
	public AnswerListAdapter(Context context, int textViewResourceId, List<Answer> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Answer mAnswer = getItem(position);
		
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView tv_answerer= (TextView) view.findViewById(R.id.txt_answerer_answeritem);
		TextView tv_context = (TextView) view.findViewById(R.id.txt_answercontext_answeritem);
		TextView tv_date = (TextView) view.findViewById(R.id.txt_date_answeritem);


		tv_answerer.setText(mAnswer.getaAnswerer());
		tv_context.setText(mAnswer.getaContext());
		tv_date.setText(mAnswer.getCreatedAt());
		
		
		return view;
	}
}
