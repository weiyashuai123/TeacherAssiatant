package com.teacherassistant.util;

import android.app.Activity;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

public class BaseActivity extends Activity{
	
	public void DBInit(){
		//���������ݿ�
		Bmob.initialize(this, "0ccf8db1aae9d4bea0f717caa46d9719");
	}
	public void toast(String message){
		//��toast
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
