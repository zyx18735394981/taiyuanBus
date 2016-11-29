package com.zyx.utils;



import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 不允许滑动的viewpager
 * @author Administrator
 *
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		
		return false;//不拦截子控件的事件
	}
//子类重写了父类的构造方法,触摸时什么都不做,就把滑动事件禁用了
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {

		return true;
	}
}
