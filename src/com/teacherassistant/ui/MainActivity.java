package com.teacherassistant.ui;

import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.R;
import com.teacherassistant.ui.fragment.Fragment_callname;
import com.teacherassistant.ui.fragment.Fragment_job;
import com.teacherassistant.ui.fragment.Fragment_question;
//import com.teacherassistant.view.ViewPagerIndicator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener, OnMenuItemClickListener {

	private ViewPager mViewPager;
//	private ViewPagerIndicator mIndicator;

	private List<Fragment> mContents = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;

	private ImageView img_callname;
	private ImageView img_question;
	private ImageView img_job;
	private TextView tv_callname;
	private TextView tv_question;
	private TextView tv_job;

	private LinearLayout ll_callname;
	private LinearLayout ll_question;
	private LinearLayout ll_job;

	private TextView tv_title;
	private ImageView img_menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initDatas();
		initViews();
		setViewPager();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ll_callname:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.ll_question:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.ll_job:
			mViewPager.setCurrentItem(2);
			break;
		case R.id.img_menu_main:
			PopupMenu popup = new PopupMenu(MainActivity.this, v); // 建立PopupMenu对象
			popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
			popup.setOnMenuItemClickListener(MainActivity.this); // 设置点击菜单选项事件
			popup.show(); // 显示菜单
		default:
			break;
		}
	}

	private void initViews() {

		mViewPager = (ViewPager) findViewById(R.id.viwepager_main);
//		mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);

		tv_title = (TextView) findViewById(R.id.txt_title_mian);

		tv_callname = (TextView) findViewById(R.id.txt_callname_main);
		tv_question = (TextView) findViewById(R.id.txt_question_main);
		tv_job = (TextView) findViewById(R.id.txt_job_main);
		img_callname = (ImageView) findViewById(R.id.img_callname_main);
		img_question = (ImageView) findViewById(R.id.img_question_main);
		img_job = (ImageView) findViewById(R.id.img_job_main);
		img_menu = (ImageView) findViewById(R.id.img_menu_main);

		ll_callname = (LinearLayout) findViewById(R.id.ll_callname);
		ll_job = (LinearLayout) findViewById(R.id.ll_job);
		ll_question = (LinearLayout) findViewById(R.id.ll_question);

		ll_callname.setOnClickListener(this);
		ll_job.setOnClickListener(this);
		ll_question.setOnClickListener(this);
		img_menu.setOnClickListener(this);
	}

	//
	private void initDatas() {
		// TODO Auto-generated method stub

		Fragment_callname fragment_callname = new Fragment_callname();
		Fragment_question fragment_question = new Fragment_question();
		Fragment_job fragment_job = new Fragment_job();
		mContents.add(fragment_callname);
		mContents.add(fragment_question);
		mContents.add(fragment_job);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mContents.size();
			}

			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return mContents.get(position);
			}
		};

	}

	private void setViewPager() {
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOffscreenPageLimit(2);// 设置预加载两个界面
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				switch (position) {
				case 0:
					tv_title.setText("点名");
					tv_callname.setTextColor(Color.parseColor("#1296db"));
					tv_question.setTextColor(Color.parseColor("#8a8a8a"));
					tv_job.setTextColor(Color.parseColor("#8a8a8a"));
					img_callname.setImageResource(R.drawable.ic_callname_blue);
					img_question.setImageResource(R.drawable.ic_question_gray);
					img_job.setImageResource(R.drawable.ic_job_gray);
					break;
				case 1:
					tv_title.setText("答疑");
					tv_callname.setTextColor(Color.parseColor("#8a8a8a"));
					tv_question.setTextColor(Color.parseColor("#1296db"));
					tv_job.setTextColor(Color.parseColor("#8a8a8a"));
					img_callname.setImageResource(R.drawable.ic_callname_gray);
					img_question.setImageResource(R.drawable.ic_question_blue);
					img_job.setImageResource(R.drawable.ic_job_gray);
					break;
				case 2:
					tv_title.setText("布置作业");
					tv_callname.setTextColor(Color.parseColor("#8a8a8a"));
					tv_question.setTextColor(Color.parseColor("#8a8a8a"));
					tv_job.setTextColor(Color.parseColor("#1296db"));
					img_callname.setImageResource(R.drawable.ic_callname_gray);
					img_question.setImageResource(R.drawable.ic_question_gray);
					img_job.setImageResource(R.drawable.ic_job_blue);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// mIndicator.scroll(position, positionOffset);
				// if (positionOffset>0) {
				// switch (position) {
				// case 0:
				// img_callname.setImageResource(R.drawable.ic_callname_blue);
				// img_question.setImageResource(R.drawable.ic_question_blue);
				// tv_callname.setTextColor(Color.parseColor("#1296db"));
				// tv_question.setTextColor(Color.parseColor("#1296db"));
				// img_callname.setAlpha(1-positionOffset);
				// img_question.setAlpha(positionOffset);
				// break;
				// case 1:
				// img_job.setImageResource(R.drawable.ic_job_blue);
				// img_question.setImageResource(R.drawable.ic_question_blue);
				// img_job.setAlpha(positionOffset);
				// img_question.setAlpha(1-positionOffset);
				// break;
				// default:
				// break;
				// }
				// }
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add_class_MainMenu:
			Intent add_class = new Intent(MainActivity.this,CreateClassActivity.class);
			startActivity(add_class);
			break;
		case R.id.check_namelist_MainMenu:
			toast("查看名单");
			break;
		case R.id.history_MainMenu:
			Intent record = new Intent(MainActivity.this, RecordActivity.class);
			startActivity(record);
			break;

		default:
			break;
		}

		return false;
	}
	private void toast(String message){
		//简化toast
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
