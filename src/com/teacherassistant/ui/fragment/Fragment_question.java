package com.teacherassistant.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Question;
import com.teacherassistant.adapter.QuestionListAdapter;
import com.teacherassistant.ui.AnswerQuestionActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Fragment_question extends Fragment {

	private View view;
	private TextView tv_title_allQ;
	private TextView tv_title_noAnswerQ;
	private TextView tv_title_AnsweredQ;
	private ImageView img_refresh;

	private int chooser = 1;

	private ListView lv_questionlist;
	private List<Question> queslist = new ArrayList<Question>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_question_main, container, false);
		initviews();
		initdata();
		initaction();

		return view;
	}

	private void initaction() {
		// TODO Auto-generated method stub
		img_refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setdata(chooser);
				toast("问题列表已更新");
			}
		});
		tv_title_allQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chooser = 1;
				settitle(chooser);
				setdata(chooser);
			}
		});
		tv_title_AnsweredQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chooser = 2;
				settitle(chooser);
				setdata(chooser);
			}
		});
		tv_title_noAnswerQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chooser = 3;
				settitle(chooser);
				setdata(chooser);
			}
		});
		lv_questionlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Question mQuestion = queslist.get(position);
				Intent intent = new Intent(getActivity(), AnswerQuestionActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("question", mQuestion);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	protected void settitle(int chooser) {
		// TODO Auto-generated method stub
		tv_title_allQ.setBackgroundResource(R.drawable.bg_question_normal);
		tv_title_AnsweredQ.setBackgroundResource(R.drawable.bg_question_normal);
		tv_title_noAnswerQ.setBackgroundResource(R.drawable.bg_question_normal);
		tv_title_allQ.setTextColor(Color.parseColor("#8a8a8a"));
		tv_title_AnsweredQ.setTextColor(Color.parseColor("#8a8a8a"));
		tv_title_noAnswerQ.setTextColor(Color.parseColor("#8a8a8a"));
		switch (chooser) {
		case 1:
			tv_title_allQ.setBackgroundResource(R.drawable.bg_questionindicator);
			tv_title_allQ.setTextColor(Color.parseColor("#2995f5"));
			break;
		case 2:
			tv_title_AnsweredQ.setBackgroundResource(R.drawable.bg_questionindicator);
			tv_title_AnsweredQ.setTextColor(Color.parseColor("#2995f5"));
			break;
		case 3:
			tv_title_noAnswerQ.setBackgroundResource(R.drawable.bg_questionindicator);
			tv_title_noAnswerQ.setTextColor(Color.parseColor("#2995f5"));
			break;

		default:
			break;
		}
	}

	private void initdata() {
		// TODO Auto-generated method stub
		BmobQuery<Question> query = new BmobQuery<Question>();
		query.order("-createdAt");
		query.findObjects(new FindListener<Question>() {

			@Override
			public void done(List<Question> list, BmobException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					for (int i = 0; i < list.size(); i++) {
						Question question = list.get(i);
						queslist.add(question);
						QuestionListAdapter adapter = new QuestionListAdapter(getActivity(), R.layout.item_questionlist,
								queslist);
						lv_questionlist.setAdapter(adapter);
					}

				} else {
					toast("列表初始化错误：" + e.getMessage());
				}
			}
		});

	}

	private void initviews() {
		// TODO Auto-generated method stub
		img_refresh = (ImageView) view.findViewById(R.id.img_refresh_question);
		tv_title_allQ = (TextView) view.findViewById(R.id.txt_allquestion);
		tv_title_noAnswerQ = (TextView) view.findViewById(R.id.txt_noanswer_question);
		tv_title_AnsweredQ = (TextView) view.findViewById(R.id.txt_answered_question);

		lv_questionlist = (ListView) view.findViewById(R.id.list_question);

	}

	private void toast(String massage) {
		Toast.makeText(getActivity(), massage, Toast.LENGTH_SHORT).show();
	}

	private void setdata(int chooser) {
		// TODO Auto-generated method stub

		switch (chooser) {
		case 1:
			BmobQuery<Question> query = new BmobQuery<Question>();
			query.order("-createdAt");
			query.findObjects(new FindListener<Question>() {

				@Override
				public void done(List<Question> list, BmobException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						queslist.clear();
						for (int i = 0; i < list.size(); i++) {
							Question question = list.get(i);
							queslist.add(question);
							QuestionListAdapter adapter = new QuestionListAdapter(getActivity(),
									R.layout.item_questionlist, queslist);
							lv_questionlist.setAdapter(adapter);
						}

					} else {
						toast("列表初始化错误：" + e.getMessage());
					}
				}
			});
			break;
		case 3:
			BmobQuery<Question> query2 = new BmobQuery<Question>();
			query2.addWhereEqualTo("state", "未解决");
			query2.order("-createdAt");
			query2.findObjects(new FindListener<Question>() {

				@Override
				public void done(List<Question> list, BmobException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						queslist.clear();
						for (int i = 0; i < list.size(); i++) {
							Question question = list.get(i);
							queslist.add(question);
							QuestionListAdapter adapter = new QuestionListAdapter(getActivity(),
									R.layout.item_questionlist, queslist);
							lv_questionlist.setAdapter(adapter);
						}

					} else {
						toast("列表初始化错误：" + e.getMessage());
					}
				}
			});
			break;
		case 2:
			break;
		default:
			break;
		}

	}
}
