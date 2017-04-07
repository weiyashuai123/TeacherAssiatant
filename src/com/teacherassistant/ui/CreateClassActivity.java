package com.teacherassistant.ui;

import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Classes;
import com.teacherassistant.DBmodel.SignInfo;
import com.teacherassistant.util.BaseActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class CreateClassActivity extends BaseActivity implements OnClickListener {

	private Button btn_ok;
	private EditText edt_classname;
	private ImageView img_back;

	private String classname;
	private String teacher;
	private boolean res = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createclass);

		DBInit();
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		btn_ok = (Button) findViewById(R.id.btn_ok_createclass);
		edt_classname = (EditText) findViewById(R.id.edt_classname_create);
		img_back = (ImageView) findViewById(R.id.img_back_createclass);

		btn_ok.setOnClickListener(this);
		img_back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok_createclass:

			checkclass();

			break;
		case R.id.img_back_createclass:
			CreateClassActivity.this.finish();
			break;
		default:
			break;
		}
	}

	private void checkclass() {

		SharedPreferences sp = getSharedPreferences("RememberUser", MODE_PRIVATE);
		classname = edt_classname.getText().toString();
		teacher = sp.getString("username", "");
		if (classname.isEmpty()) {
			toast("群组名不能为空！");
		} else {
			BmobQuery<Classes> bq1 = new BmobQuery<Classes>();
			bq1.addWhereEqualTo("name", classname);
			BmobQuery<Classes> bq2 = new BmobQuery<Classes>();
			bq2.addWhereEqualTo("teacher", teacher);
			List<BmobQuery<Classes>> andQuerys = new ArrayList<BmobQuery<Classes>>();
			andQuerys.add(bq1);
			andQuerys.add(bq2);
			BmobQuery<Classes> query = new BmobQuery<Classes>();
			query.and(andQuerys);
			query.findObjects(new FindListener<Classes>() {
				
				@Override
				public void done(List<Classes> list, BmobException e) {
					// TODO Auto-generated method stub
					if(e == null){
						if (list.size()==0) {
							CreatedClass();
						}
						else{
							toast("该群组已存在，请重新输入");
						}
					}else{
						toast("错误"+e.getMessage());
					}
				}
			});
		}

	}

	private void CreatedClass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder mdialog = new Builder(CreateClassActivity.this);
		mdialog.setTitle("创建群组");
		mdialog.setMessage("确定创建群组'" + classname + "'吗？");
		mdialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Classes aclass = new Classes(classname, teacher);
				aclass.save(new SaveListener<String>() {

					@Override
					public void done(String arg0, BmobException e) {
						// TODO Auto-generated method stub
						if (e == null) {

							SharedPreferences.Editor spe = getSharedPreferences("ClassList", MODE_PRIVATE).edit();
							SharedPreferences sp = getSharedPreferences("ClassList", MODE_PRIVATE);
							int num = sp.getInt("ClassNumber", 0);
							spe.putInt("ClassNumber", num + 1);
							spe.putString("class" + num, classname);
							spe.putInt("people" + num, 0);
							spe.commit();
							toast("群组建立成功！");
							CreateClassActivity.this.finish();
						} else {
							toast("失败：" + e.getMessage());
						}
					}
				});
			}
		});
		mdialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				toast("取消创建.");
			}
		});
		mdialog.show();
	}
}
