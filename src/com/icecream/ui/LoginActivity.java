package com.icecream.ui;
/**
 * 登录界面，实现登录的功能以及往注册界面的跳转
 *
 * @author ${魏亚帅}
 * @date ${2016年9月24日10:24}
 */
import com.icecream.TeacherAssistant.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private final String WRONG_USER_INFO = "用户名或密码错误！";
	//用户名或密码错误的提示
	private final String WRONG_CONNECT = "无法访问服务器，请检查网络连接！";
	//无网络连接的提示
	private final String WRONG_OTHERS = "登录失败，未知的错误！";
	//其他错误的提示
	private final String SUCCESS = "登陆成功！"; 
	//登陆成功的提示
	
	private EditText edt_username;
	private EditText edt_password;
	private TextView txt_signup;
	private TextView txt_forgetpassword;
	private ProgressDialog progress ;
	private Button btn_login;
	private CheckBox chk_rememberpass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		initView();
	}
	
	private void initView(){
		/**
		 * 初始化使用到的控件
		 */
		edt_username = (EditText) findViewById(R.id.edt_loginpage_username); 
		edt_password = (EditText) findViewById(R.id.edt_loginpage_password);
		txt_signup = (TextView) findViewById(R.id.txt_loginpage_sign);
		chk_rememberpass = (CheckBox) findViewById(R.id.chk_loginpage_rememberPass);
	}

}
