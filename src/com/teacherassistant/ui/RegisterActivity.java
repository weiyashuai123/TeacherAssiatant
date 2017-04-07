package com.teacherassistant.ui;

import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.TeacherUser;
import com.teacherassistant.util.BaseActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {

	private EditText edt_username;
	private EditText edt_password;
	private EditText edt_passagain;
	private EditText edt_phonenumber;
	private Button btn_register;
	private ImageView img_back;
	private ProgressDialog progressDialog;

	private String username;
	private String password;
	private String phonenumber;
	private String passagain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		DBInit();
		initViews();
	}

	private void initViews() {
		// ��ʼ���ؼ�
		edt_passagain = (EditText) findViewById(R.id.edt_passagain_register);
		edt_password = (EditText) findViewById(R.id.edt_password_register);
		edt_username = (EditText) findViewById(R.id.edt_username_register);
		edt_phonenumber = (EditText) findViewById(R.id.edt_phone_register);
		btn_register = (Button) findViewById(R.id.btn_register_register);
		img_back = (ImageView) findViewById(R.id.img_back_register);
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toRegister();
			}
		});
		img_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish();
			}
		});
	}

	private void toRegister() {
		// TODO Auto-generated method stub
		// ע���¼�
		progressDialog = new ProgressDialog(RegisterActivity.this);
		progressDialog.setMessage("���ע����Ϣ...");
		progressDialog.show();

		username = edt_username.getText().toString();
		password = edt_password.getText().toString();
		phonenumber = edt_phonenumber.getText().toString();
		passagain = edt_passagain.getText().toString();

		// ����û����Ƿ��ѱ�ע�ᣨ�����ݿ��в�ѯ�û���
		BmobQuery<TeacherUser> query = new BmobQuery<TeacherUser>();
		query.addWhereEqualTo("username", username);
		query.findObjects(new FindListener<TeacherUser>() {

			@Override
			public void done(List<TeacherUser> userlist, BmobException e) {
				// TODO Auto-generated method stub
				if (username.isEmpty() || password.isEmpty() || passagain.isEmpty() || phonenumber.isEmpty()) {
					toast("�뽫��Ϣ��д������");
					progressDialog.dismiss();
				} else if (!passagain.equals(password)) {
					progressDialog.dismiss();
					toast("������������벻һ�£�");
				} else if (userlist.size() > 0) {
					progressDialog.dismiss();
					toast("�û����Ѵ��ڣ�����������");
				} else {
					progressDialog.setMessage("����ע��...");
					TeacherUser teacher = new TeacherUser(username, password, phonenumber);
					teacher.save(new SaveListener<String>() {

						@Override
						public void done(String message, BmobException e) {
							// TODO Auto-generated method stub
							if (e == null) {
								progressDialog.dismiss();
								toast("ע��ɹ������¼��");
								RegisterActivity.this.finish();
							} else {
								progressDialog.dismiss();
								toast("ʧ�ܣ�" + e.getMessage());
							}
						}
					});
				}
			}
		});
	}

}
