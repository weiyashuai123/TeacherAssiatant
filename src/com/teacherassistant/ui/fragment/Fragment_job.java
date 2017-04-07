package com.teacherassistant.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Answer;
import com.teacherassistant.DBmodel.Job;
import com.teacherassistant.adapter.JobListAdapter;
import com.teacherassistant.ui.CreateJobActivity;
import com.teacherassistant.ui.UpdateJobActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class Fragment_job extends Fragment {

	private View view;
	
	private ListView lv_job;
	private Button btn_addjob;
	private ImageView img_refresh;
	
	private List<Job> joblist = new ArrayList<Job>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_job_main, container, false);
		
		initview();
		initaction();
		initdaata();
		return view;
	}

	private void initdaata() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getActivity().getSharedPreferences("RememberUser", Context.MODE_PRIVATE);
		BmobQuery<Job> query = new BmobQuery<Job>();
		query.addWhereEqualTo("teacher", sp.getString("username", ""));
		query.order("-createdAt");
		query.findObjects(new FindListener<Job>() {
			
			@Override
			public void done(List<Job> list, BmobException e) {
				// TODO Auto-generated method stub
				if (e==null) {
					for (int i = 0; i < list.size(); i++) {
						Job job = list.get(i);
						joblist.add(job);
					}
					JobListAdapter adapter = new JobListAdapter(getActivity(), R.layout.item_joblist, joblist);
					lv_job.setAdapter(adapter);
				} else {
					Toast.makeText(getActivity(), "数据初始化错误："+e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void initaction() {
		// TODO Auto-generated method stub
		lv_job.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Job job = joblist.get(position);
				Intent intent = new Intent(getActivity(), UpdateJobActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("job", job);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		btn_addjob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), CreateJobActivity.class);
				startActivity(intent);
			}
		});
		img_refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				joblist.clear();
				initdaata();
			}
		});
	}

	private void initview() {
		// TODO Auto-generated method stub
		img_refresh = (ImageView) view.findViewById(R.id.img_refresh_job);
		lv_job = (ListView) view.findViewById(R.id.list_job);
		btn_addjob = (Button) view.findViewById(R.id.btn_add_job);
	}
	
}
