package com.teacherassistant.ui;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Job;
import com.teacherassistant.util.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateJobActivity extends BaseActivity implements OnClickListener{
	
	private ImageView img_back;
	private EditText edt_course;
	private EditText edt_content;
	private Button btn_delete;
	private Button btn_update;
	
	private Job job;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatejob);
		
		initView();
		initData();
		initAction();
	}

	private void initAction() {
		// TODO Auto-generated method stub
		img_back.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
		btn_update.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		job = (Job) intent.getSerializableExtra("job");
		edt_content.setText(job.getJobContent());
		edt_course.setText(job.getCourseName());
	}

	private void initView() {
		// TODO Auto-generated method stub
		img_back = (ImageView) findViewById(R.id.img_back_updatejob);
		edt_content = (EditText) findViewById(R.id.edt_content_updatejob);
		edt_course = (EditText) findViewById(R.id.edt_course_updatejob);
		btn_delete = (Button) findViewById(R.id.btn_delete_updatejob);
		btn_update = (Button) findViewById(R.id.btn_update_updatejob);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_delete_updatejob:
			deletejob();
			break;
		case R.id.btn_update_updatejob:
			updatejob();
			break;
		case R.id.img_back_updatejob:
			UpdateJobActivity.this.finish();
			break;
		default:
			break;
		}
	}

	private void deletejob() {
		// TODO Auto-generated method stub
		job.delete(new UpdateListener() {
			
			@Override
			public void done(BmobException e) {
				// TODO Auto-generated method stub
				if (e==null) {
					toast("删除成功");
					UpdateJobActivity.this.finish();
				} else {
					toast("错误："+e.getMessage());
				}
			}
		});
	}

	private void updatejob() {
		// TODO Auto-generated method stub
		String course = edt_course.getText().toString();
		String content = edt_content.getText().toString();
		job.setCourseName(course);
		job.setJobContent(content);
		job.update(new UpdateListener() {
			
			@Override
			public void done(BmobException e) {
				// TODO Auto-generated method stub
				if (e==null) {
					toast("更新成功");
					UpdateJobActivity.this.finish();
				} else {
					toast("错误："+e.getMessage());
				}
			}
		});
	}
}
