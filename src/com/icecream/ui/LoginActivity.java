package com.icecream.ui;
/**
 * ��¼���棬ʵ�ֵ�¼�Ĺ����Լ���ע��������ת
 *
 * @author ${κ��˧}
 * @date ${2016��9��24��10:24}
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
	private final String WRONG_USER_INFO = "�û������������";
	//�û���������������ʾ
	private final String WRONG_CONNECT = "�޷����ʷ������������������ӣ�";
	//���������ӵ���ʾ
	private final String WRONG_OTHERS = "��¼ʧ�ܣ�δ֪�Ĵ���";
	//�����������ʾ
	private final String SUCCESS = "��½�ɹ���"; 
	//��½�ɹ�����ʾ
	
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
		 * ��ʼ��ʹ�õ��Ŀؼ�
		 */
		edt_username = (EditText) findViewById(R.id.edt_loginpage_username); 
		edt_password = (EditText) findViewById(R.id.edt_loginpage_password);
		txt_signup = (TextView) findViewById(R.id.txt_loginpage_sign);
		chk_rememberpass = (CheckBox) findViewById(R.id.chk_loginpage_rememberPass);
	}

}
