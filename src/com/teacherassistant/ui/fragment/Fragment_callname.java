package com.teacherassistant.ui.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.DBmodel.Classes;
import com.teacherassistant.DBmodel.SignInfo;
import com.teacherassistant.DBmodel.SignRecord;
import com.teacherassistant.DBmodel.TeacherUser;
import com.teacherassistant.util.GetDistance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_callname extends Fragment {

	private View view;
	private Button btn_startcall;
	private Button btn_endcall;
	private TextView tv_console;
	private TextView tv_tips;
	private TextView tv_state;
	private TextView tv_class;
	private TextView tv_empty;

	private ListView list_class;
	private String mName;
	private SimpleAdapter classlist_adapter;
	private Dialog classlist_dialog;
	private List<HashMap<String, Object>> mapLists = null;

	private String pNumber = "0";
	private String provider;
	private LocationManager locationManager;
	public static final int TIP_UPDATE = 1;

	private String teacher;
	private String classname;

	private final int STATE_START = 1;
	private final int STATE_END = 0;

	private int state = STATE_END;
	private String info;
	private List<Classes> classlist;
	private double latitude;
	private double longitude;

	private Date startTime;

	private int tips_id = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_callname_main, container, false);
		tv_console = (TextView) view.findViewById(R.id.txt_console_callname);
		tv_console.setMovementMethod(ScrollingMovementMethod.getInstance());

		tv_tips = (TextView) view.findViewById(R.id.tips_callname);
		tv_state = (TextView) view.findViewById(R.id.txt_state_callname);
		tv_class = (TextView) view.findViewById(R.id.txt_class_callname);
		tv_empty = (TextView) view.findViewById(R.id.txt_empty_console);

		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		List<String> providerList = locationManager.getProviders(true);
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		}
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);

		tv_class.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (hasclass()) {
					showDialog();
				} else {
					Toast.makeText(getActivity(), "您没有建立任何群组", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn_startcall = (Button) view.findViewById(R.id.btn_start_callname);
		btn_endcall = (Button) view.findViewById(R.id.btn_end_callname);
		btn_startcall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences sp = getActivity().getSharedPreferences("RememberUser", Context.MODE_PRIVATE);
				classname = tv_class.getText().toString();
				teacher = sp.getString("username", "");

				if (!tv_class.getText().toString().equals("点击选择群组")) {
					BmobQuery<Classes> eq1 = new BmobQuery<Classes>();
					eq1.addWhereEqualTo("name", classname);
					BmobQuery<Classes> eq2 = new BmobQuery<Classes>();
					eq2.addWhereEqualTo("teacher", teacher);
					List<BmobQuery<Classes>> andQuerys = new ArrayList<BmobQuery<Classes>>();
					andQuerys.add(eq1);
					andQuerys.add(eq2);
					BmobQuery<Classes> query = new BmobQuery<Classes>();
					query.and(andQuerys);
					query.findObjects(new FindListener<Classes>() {

						@Override
						public void done(List<Classes> list, BmobException e) {
							// TODO Auto-generated method stub
							if (e == null) {
								Classes cla = list.get(0);
								if (state == STATE_END) {
									cla.setState("开始");
									startTime = new Date();
									cla.setStarttime(new BmobDate(startTime));
									cla.update(new UpdateListener() {
										
										@Override
										public void done(BmobException e) {
											// TODO Auto-generated method stub
											if(e==null){
												info = tv_console.getText().toString();
												info = info + tv_class.getText().toString() + "  点名进行中... " + "\n";
												tv_console.setText(info);
												state = STATE_START;
												tv_state.setText("正在点名");
											}
											else{
												Toast.makeText(getActivity(), "错误:"+e.getMessage(), Toast.LENGTH_SHORT).show();
											}
										}
									});
								} else {
									Toast.makeText(getActivity(), "点名已开始，请不要重复点击", Toast.LENGTH_SHORT).show();
								}
							} else {

								Toast.makeText(getActivity(), "错误:"+e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}
					});
				} else {
					Toast.makeText(getActivity(), "请先选择群组", Toast.LENGTH_SHORT).show();
				}

			}
		});

		btn_endcall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (state == STATE_START) {
					tv_state.setText("未开始点名");
					info = tv_console.getText().toString();
					info = info + "点名结束，本次签到名单： " + "\n";
					List<String> providerList = locationManager.getProviders(true);
					if (providerList.contains(LocationManager.GPS_PROVIDER)) {
						provider = LocationManager.GPS_PROVIDER;
					} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
						provider = LocationManager.NETWORK_PROVIDER;
					}else{
						Toast.makeText(getActivity(), "GPS故障，将以默认值进行", Toast.LENGTH_SHORT).show();
					}

					Location location = locationManager.getLastKnownLocation(provider);
					latitude = 37.87241;
					longitude = 112.484756;
					if (location != null) {
						latitude = location.getLatitude();
						longitude = location.getLongitude();
					}

					BmobQuery<SignInfo> eq1 = new BmobQuery<SignInfo>();
					eq1.addWhereEqualTo("classname", tv_class.getText().toString());
					BmobQuery<SignInfo> eq2 = new BmobQuery<SignInfo>();
					eq2.addWhereEqualTo("teacher", mName);
					BmobQuery<SignInfo> eq3 = new BmobQuery<SignInfo>();
					eq3.addWhereGreaterThanOrEqualTo("createdAt", new BmobDate(startTime));
					List<BmobQuery<SignInfo>> andQuerys = new ArrayList<BmobQuery<SignInfo>>();
					andQuerys.add(eq1);
					andQuerys.add(eq2);
					andQuerys.add(eq3);
					BmobQuery<SignInfo> query = new BmobQuery<SignInfo>();
					query.and(andQuerys);
					query.findObjects(new FindListener<SignInfo>() {

						@Override
						public void done(List<SignInfo> list, BmobException e) {
							// TODO Auto-generated method stub
							
							if (e == null) {
								String namelist = "";
								for (int i = 0; i < list.size(); i++) {
									SignInfo signinfo = list.get(i);
									String distance = GetDistance.getdistance(latitude, longitude,
											signinfo.getLatitude(), signinfo.getLongititude());
									info = info + signinfo.getStuid()+" "+signinfo.getStuRealname() + "  " + signinfo.getState() + "  距离:"
											+ distance + "\n";
									namelist = namelist+signinfo.getStuid()+" "+signinfo.getStuRealname() + "  " + signinfo.getState() + "  距离:"
											+ distance + "\n";

								}
								
								String simple = "";
								simple = tv_class.getText().toString() + pNumber + " " + "，本次签到" + list.size()
								+ "人。" + "\n";
								info = info + tv_class.getText().toString() + pNumber + " " + "，本次签到" + list.size()
										+ "人。" + "\n";
								
								tv_console.setText(info);
								SharedPreferences sp = getActivity().getSharedPreferences("RememberUser", Context.MODE_PRIVATE);
								SignRecord sr = new SignRecord();
								sr.setClassname(tv_class.getText().toString());
								sr.setNamelist(namelist);
								sr.setSimple(simple);
								sr.setTeacher(sp.getString("username", ""));
								sr.save(new SaveListener<String>() {
									
									@Override
									public void done(String arg0, BmobException e) {
										// TODO Auto-generated method stub
										if (e==null) {
											Toast.makeText(getActivity(), "记录已录入后台", Toast.LENGTH_SHORT).show();
										} else {

										}
									}
								});
								
								state = STATE_END;
							}
						}
					});

				} else {
					Toast.makeText(getActivity(), "点名尚未开始，不能执行此操作", Toast.LENGTH_SHORT).show();
				}
			}
		});

		tv_empty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_console.setText("");
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!getActivity().isFinishing()) {

					try {
						Thread.sleep(5000);
						Message message = new Message();
						message.what = TIP_UPDATE;
						handler.sendMessage(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return view;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case TIP_UPDATE:
				switch (tips_id) {
				case 0:
					tv_tips.setText("Tips:点击右上角加号可建立群组");
					tips_id++;
					tips_id = tips_id % 3;
					break;
				case 1:
					tv_tips.setText("Tips:选择群组后才可以开始点名");
					tips_id++;
					tips_id = tips_id % 3;
					break;
				case 2:
					tv_tips.setText("Tips:点名结束后可对签到名单进行操作");
					tips_id++;
					tips_id = tips_id % 3;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	};

	private boolean hasclass() {
		SharedPreferences sp = getActivity().getSharedPreferences("ClassList", Context.MODE_PRIVATE);
		int classNumber = sp.getInt("ClassNumber", 0);
		boolean res = false;
		if (classNumber > 0) {
			mapLists = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < classNumber; i++) {
				String className = sp.getString("class" + i, "");
				int peopleNumber = sp.getInt("people" + i, 0);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", className);
				map.put("number", "共" + peopleNumber + "人");
				mapLists.add(map);
			}
			res = true;
		}
		return res;
	}

	public void showDialog() {
		classlist_dialog = new Dialog(getActivity());
		classlist_dialog.setTitle("请选择班级：");
		classlist_dialog.show();

		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View viewDialog = inflater.inflate(R.layout.dialog_classlist_callname, null);
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		// 设置对话框的宽高
		LayoutParams layoutParams = new LayoutParams(width * 80 / 100, LayoutParams.WRAP_CONTENT);
		classlist_dialog.setContentView(viewDialog, layoutParams);

		list_class = (ListView) viewDialog.findViewById(R.id.classlist_dialog);

		classlist_adapter = new SimpleAdapter(getActivity(), mapLists, R.layout.item_classlist_dialog,
				new String[] { "name", "number" }, new int[] { R.id.txt_name_classlist, R.id.txt_number_classlist });
		list_class.setAdapter(classlist_adapter);

		list_class.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				HashMap<String, Object> selectMap = mapLists.get(position);
				String name = selectMap.get("name").toString();
				pNumber = selectMap.get("number").toString();
				tv_class.setText(name);
				classlist_dialog.dismiss();
			}

		});

	}

	LocationListener locationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// Toast.makeText(MainActivity.this, "00",
			// Toast.LENGTH_SHORT).show();
		}
	};
}
