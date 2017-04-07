package com.teacherassistant.ui;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Job;
import com.teacherassistant.util.BaseActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateJobActivity extends BaseActivity implements OnClickListener {

	private EditText edt_course;
	private EditText edt_content;
	private Button btn_ok;
	private ImageView img_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createjob);

		initView();
		initAction();
	}

	private void initAction() {
		// TODO Auto-generated method stub
		img_back.setOnClickListener(this);
		btn_ok.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		edt_content = (EditText) findViewById(R.id.edt_content_createjob);
		edt_course = (EditText) findViewById(R.id.edt_course_createjob);
		img_back = (ImageView) findViewById(R.id.img_back_createjob);
		btn_ok = (Button) findViewById(R.id.btn_ok_createjob);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok_createjob:
			createJob();
			break;
		case R.id.img_back_createjob:
			CreateJobActivity.this.finish();
			break;

		default:
			break;
		}
	}

	private void createJob() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("RememberUser", MODE_PRIVATE);
		String course = edt_course.getText().toString();
		String content = edt_content.getText().toString();
		
		if (course.isEmpty()||content.isEmpty()) {
			toast("课程名称和内容不能为空");
		} else {
			Job job = new Job();
			job.setCourseName(course);
			job.setJobContent(content);
			job.setTeacher(sp.getString("username", ""));
			job.save(new SaveListener<String>() {
				
				@Override
				public void done(String arg0, BmobException e) {
					// TODO Auto-generated method stub
					if (e==null) {
						toast("作业已成功发布！");
						CreateJobActivity.this.finish();
					} else {
						toast("错误："+e.getMessage());
					}
				}
			});
		}
	}

}
