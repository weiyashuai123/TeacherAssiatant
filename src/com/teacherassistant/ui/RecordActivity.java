package com.teacherassistant.ui;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.SignRecord;
import com.teacherassistant.adapter.SignlistAdapter;
import com.teacherassistant.util.BaseActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RecordActivity extends BaseActivity{
	
	private ImageView img_back;
	private ListView lv_signinfo;
	private List<SignRecord> signlist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		
		
		lv_signinfo = (ListView) findViewById(R.id.list_record);
		img_back = (ImageView) findViewById(R.id.img_back_recordAct);
		img_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordActivity.this.finish();
			}
		});
		
		SharedPreferences sp = getSharedPreferences("RememberUser", Context.MODE_PRIVATE);
		BmobQuery<SignRecord> query = new BmobQuery<SignRecord>();
		query.addWhereEqualTo("teacher", sp.getString("username", ""));
		query.order("-createdAt");
		query.findObjects(new FindListener<SignRecord>() {
			
			@Override
			public void done(List<SignRecord> list, BmobException e) {
				// TODO Auto-generated method stub
				if (e==null) {
					signlist =list; 
					SignlistAdapter adapter = new SignlistAdapter(RecordActivity.this, R.layout.item_recordlist, signlist);
					lv_signinfo.setAdapter(adapter);
					
				} else {
					toast("Êý¾Ý²éÑ¯Ê§°Ü£¡"+e.getMessage());
				}
			}
		});
		
		lv_signinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				SignRecord sr = signlist.get(position);
				toast(sr.getNamelist()+".");
//			ff	Dialog dialog=new AlertDialog.Builder(this).setTitle(sr.getNamelist());
			}
		});
	}

}
