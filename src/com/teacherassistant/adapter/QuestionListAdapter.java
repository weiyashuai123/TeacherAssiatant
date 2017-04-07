package com.teacherassistant.adapter;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Question;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class QuestionListAdapter extends ArrayAdapter<Question>{


	private int resourceId ;
	public QuestionListAdapter(Context context, int textViewResourceId, List<Question> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Question mQuestion = getItem(position);
		
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView tv_qname = (TextView) view.findViewById(R.id.txt_qname_questionlist);
		TextView tv_qanswernum = (TextView) view.findViewById(R.id.txt_qanswernum_questionlist);
		TextView tv_qproposer = (TextView) view.findViewById(R.id.txt_qproposer_questionlist);
		TextView tv_qdescription = (TextView) view.findViewById(R.id.txt_qdescript_questionlist);
		TextView tv_state = (TextView) view.findViewById(R.id.txt_state_questionlist);
		TextView tv_date = (TextView) view.findViewById(R.id.txt_date_questionlist);

		tv_qname.setText(mQuestion.getqName());
		tv_qanswernum.setText(""+mQuestion.getqAnswerNumber());
		tv_qproposer.setText(mQuestion.getqProposer());
		tv_qdescription.setText(mQuestion.getqDescription());
		tv_state.setText(mQuestion.getState());
		tv_date.setText(mQuestion.getCreatedAt().toString());
		
		if (mQuestion.getState().equals("Î´½â¾ö")) {
			tv_state.setTextColor(Color.parseColor("#E90E0E"));
		} 
		
		return view;
	}
	



}
