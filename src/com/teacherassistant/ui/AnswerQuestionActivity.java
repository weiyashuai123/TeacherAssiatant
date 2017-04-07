package com.teacherassistant.ui;

import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Answer;
import com.teacherassistant.DBmodel.Question;
import com.teacherassistant.adapter.AnswerListAdapter;
import com.teacherassistant.util.BaseActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class AnswerQuestionActivity extends BaseActivity implements OnClickListener {

	private TextView tv_qname;
	private TextView tv_qproposer;
	private TextView tv_qdescription;
	private TextView tv_qdate;
	private ImageView img_back;
	private EditText edt_answer;
	private Button btn_submit;

	private ListView lv_answer;

	private List<Answer> anslist = new ArrayList<Answer>();
	private Question question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answerquestion);
		Intent intent = getIntent();
		question = (Question) intent.getSerializableExtra("question");

		initview();
		initdata();

	}

	private void initdata() {
		// TODO Auto-generated method stub
		BmobQuery<Answer> query = new BmobQuery<Answer>();
		query.addWhereEqualTo("qId", question.getObjectId());
		query.order("-createdAt");
		query.findObjects(new FindListener<Answer>() {

			@Override
			public void done(List<Answer> list, BmobException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					if (list.size()==0) {
						toast("这个问题还没有人回答哦");
					}
					for (int i = 0; i < list.size(); i++) {
						Answer ans = list.get(i);
						anslist.add(ans);
						AnswerListAdapter adapter = new AnswerListAdapter(AnswerQuestionActivity.this,
								R.layout.item_answerliast, anslist);
						lv_answer.setAdapter(adapter);
					}
				} else {
					toast("错误：" + e.getMessage());
				}

			}
		});

	}

	private void initview() {
		// TODO Auto-generated method stub
		tv_qdate = (TextView) findViewById(R.id.txt_date_answerAct);
		tv_qdescription = (TextView) findViewById(R.id.txt_qdescription_answerAct);
		tv_qproposer = (TextView) findViewById(R.id.txt_proposer_answerAct);
		tv_qname = (TextView) findViewById(R.id.txt_qName_answerAct);

		lv_answer = (ListView) findViewById(R.id.list_answer_answeract);
		img_back = (ImageView) findViewById(R.id.img_back_answerAct);
		edt_answer = (EditText) findViewById(R.id.edt_answer_answerAct);
		btn_submit = (Button) findViewById(R.id.btn_submit_answerAct);

		tv_qdate.setText(question.getCreatedAt());
		tv_qdescription.setText(question.getqDescription());
		tv_qproposer.setText(question.getqProposer());
		tv_qname.setText(question.getqName());

		img_back.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_submit_answerAct:
			submitAnswer();
			break;
		case R.id.img_back_answerAct:
			AnswerQuestionActivity.this.finish();
			break;

		default:
			break;
		}
	}

	private void submitAnswer() {
		// TODO Auto-generated method stub
		if (edt_answer.getText().toString().isEmpty()) {
			toast("您没有填写任何内容");
		} else {
			String info = edt_answer.getText().toString();
			SharedPreferences sp = getSharedPreferences("RememberUser", MODE_PRIVATE);
			Answer aAns = new Answer();
			aAns.setqId(question.getObjectId());
			aAns.setaContext(info);
			aAns.setaAnswerer(sp.getString("username", ""));
			aAns.save(new SaveListener<String>() {
				
				@Override
				public void done(String arg0, BmobException e) {
					// TODO Auto-generated method stub
					if (e==null) {
						toast("回答成功");
						edt_answer.setText("");
						anslist.clear();
						initdata();
					} else {
						toast("错误："+e.getMessage());
					}
				}
			});
		}
		
	}

}
