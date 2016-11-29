package com.zyx.taiyuanbus;




import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import com.zyx.Fragment.ContentFragment;
import com.zyx.Fragment.LeftMenuFragment;



import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
/**
 * 主界面--带有侧边栏
 *把主界面分为两个fragment--侧边栏+主界面内容
 * @author ZYX
 *
 */
public class MainActivity extends SlidingFragmentActivity {
	
	public static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";//侧边栏的tag标签
	public static final String TAG_CONTRNT = "TAG_CONTRNT";//主界面的tag标签
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
   //设置侧边栏
		setBehindContentView(R.layout.left_menu);
				
		SlidingMenu slidingMenu=getSlidingMenu();
	//设置全屏触摸
	    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	//设置屏幕预留宽度
	   slidingMenu.setBehindOffset(200);
	   
	   //初始化fragment对象
       initFragment();
	} 
	//初始化fragment对象
	private void initFragment(){
		FragmentManager fm=getSupportFragmentManager();//得到fm对象
		FragmentTransaction transaction=fm.beginTransaction();//开启事务
		
		//替换侧边栏
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);
		//替换主界面
		transaction.replace(R.id.fl_content, new ContentFragment(), TAG_CONTRNT);
		//提交
		transaction.commit();
		
	}	        
	        
}

	 
