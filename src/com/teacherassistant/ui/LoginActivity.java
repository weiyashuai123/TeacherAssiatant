package com.teacherassistant.ui;

/**
 * 登录界面Activity
 * @date 2016年11月27日10:56:39
 * @author 魏亚帅
 * 
 * */
import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Classes;
import com.teacherassistant.DBmodel.TeacherUser;
import com.teacherassistant.util.BaseActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private TextView tv_forgetPass;
	private TextView tv_register;
	private Button btn_login;
	private CheckBox ckb_rememberPass;
	private EditText edt_username;
	private EditText edt_password;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		DBInit();// 初始化云服务
		initViews();// 初始化控件

	}

	private void initViews() {
		/**
		 * 初始化控件
		 */
		tv_forgetPass = (TextView) findViewById(R.id.forgetpass);
		tv_register = (TextView) findViewById(R.id.newuser);
		btn_login = (Button) findViewById(R.id.btn_login_login);
		ckb_rememberPass = (CheckBox) findViewById(R.id.ckb_passRemember_login);
		edt_password = (EditText) findViewById(R.id.edt_password_login);
		edt_username = (EditText) findViewById(R.id.edt_username_login);
		tv_forgetPass.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		btn_login.setOnClickListener(this);

		// 从SharedPreference文件里导出用户信息
		SharedPreferences pref = getSharedPreferences("RememberUser", MODE_PRIVATE);
		String user = pref.getString("username", "");
		Boolean passRemember = pref.getBoolean("RememberPass", false);
		if (passRemember) {
			String pass = pref.getString("password", "");
			edt_password.setText(pass);
		}
		ckb_rememberPass.setChecked(passRemember);
		edt_username.setText(user);
	}

	@Override
	public void onClick(View v) {
		/**
		 * View 的点击事件
		 */
		switch (v.getId()) {
		case R.id.forgetpass:
			toast("click forget pass");
			break;
		case R.id.newuser:
			Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent_register);
			break;
		case R.id.btn_login_login:
			toLogin();
			break;
		default:
			break;
		}

	}

	private void toLogin() {
		/**
		 * 登录按钮的事件
		 */
		// 显示提示框
		progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setMessage("检测用户名和密码...");
		progressDialog.show();

		// 验证用户名密码
		String username = edt_username.getText().toString();
		String password = edt_password.getText().toString();
		BmobQuery<TeacherUser> eq1 = new BmobQuery<TeacherUser>();
		eq1.addWhereEqualTo("username", username);
		BmobQuery<TeacherUser> eq2 = new BmobQuery<TeacherUser>();
		eq2.addWhereEqualTo("password", password);
		List<BmobQuery<TeacherUser>> andQuerys = new ArrayList<BmobQuery<TeacherUser>>();
		andQuerys.add(eq1);
		andQuerys.add(eq2);
		BmobQuery<TeacherUser> query = new BmobQuery<TeacherUser>();
		query.and(andQuerys);
		query.findObjects(new FindListener<TeacherUser>() {

			@Override
			public void done(List<TeacherUser> userlist, BmobException e) {
				// TODO Auto-generated method stub
				if (e != null) {
					progressDialog.dismiss();
					toast("错误：" + e.getMessage());
				} else {
					if (userlist.size() > 0) {
						TeacherUser teacher = userlist.get(0);
						progressDialog.setMessage("正在登陆...");

						// 记住用户名和密码 保存在SharedPreference文件里
						SharedPreferences.Editor sp = getSharedPreferences("RememberUser", MODE_PRIVATE).edit();
						sp.putString("username", teacher.getUsername());
						sp.putString("password", teacher.getPassword());
						if (ckb_rememberPass.isChecked())
							sp.putBoolean("RememberPass", true);
						else
							sp.putBoolean("RememberPass", false);
						sp.putString("realname", teacher.getRealname());
						sp.putString("userid", teacher.getObjectId());
						sp.putString("school", teacher.getSchool());
						sp.putString("phone", teacher.getPhoneNumber());
						sp.commit();

						BmobQuery<Classes> classquery = new BmobQuery<>();
						classquery.addWhereEqualTo("teacher", teacher.getUsername());
						classquery.findObjects(new FindListener<Classes>() {

							@Override
							public void done(List<Classes> classlist, BmobException e) {
								// TODO Auto-generated method stub
								if (e == null) {
									if (classlist.size() > 0) {
										SharedPreferences.Editor spe = getSharedPreferences("ClassList", MODE_PRIVATE).edit();
										spe.putInt("ClassNumber", classlist.size());
										for (int i = 0; i < classlist.size(); i++) {
											spe.putString("class"+i, classlist.get(i).getName());
											spe.putInt("people"+i, classlist.get(i).getPeoplenumber());
										}
										spe.commit();
									}
									Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
									progressDialog.dismiss();
									startActivity(intent_login);
									LoginActivity.this.finish();
								} else {
									toast("错误：" + e.getMessage());
								}
							}
						});
					} else {
						progressDialog.dismiss();
						toast("登录失败，用户名或密码错误！");
					}
				}
			}
		});

	}

}
