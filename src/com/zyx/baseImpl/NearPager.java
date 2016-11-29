package com.zyx.baseImpl;

import android.app.Activity;
import android.view.View;

import com.zyx.base.BasePager;
import com.zyx.taiyuanbus.R;

public class NearPager extends BasePager{

	public NearPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View initView() {
		View view=View.inflate(myActivity, R.layout.activity_near, null);
		//动态添加到帧布局
		//fl_base_content.addView(view);
		return view;
	}

}
