package com.zyx.base;



import com.zyx.taiyuanbus.R;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 抽取类--3个标签页的基类--基本页
 * --3个详情页的基类
 * @author Administrator
 *
 */
public class BasePager {

	public Activity myActivity;
	public FrameLayout fl_base_content;//空的帧布局对象
	public View mRootview;//当前页面的布局文件对象  
	
	public BasePager(Activity activity){
		//获取fragment页面所以来的activity
		myActivity=activity;
		
		//初始化布局
        mRootview=initView();//拿到布局赋给mRootview
	}
	
	//初始化布局
		public View initView(){
			//基类的布局文件  ===头文件+内容fragment
			View view=View.inflate(myActivity, R.layout.base_pager, null);
			fl_base_content=(FrameLayout)view.findViewById(R.id.fl_base_content);//帧布局内容--主要是替换这个
	
		    return view;
		
		}
	//初始化数据
	public void initData(){
			
		}
	
}
