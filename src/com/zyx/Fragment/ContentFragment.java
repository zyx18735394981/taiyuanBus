package com.zyx.Fragment;

import java.util.ArrayList;
import java.util.List;

import com.zyx.base.BasePager;
import com.zyx.baseImpl.BuslinePager;
import com.zyx.baseImpl.NearPager;
import com.zyx.baseImpl.SelectPager;
import com.zyx.taiyuanbus.R;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContentFragment extends BaseFragment{
private ViewPager mviewPager;
	
	private List<BasePager> list;//三个标签页的集合
	
	private RadioGroup rg_group;

	@Override
	public View initView() {
		View view=View.inflate(mActivity, R.layout.fragment_content, null);
		
		//viewpager控件
		 mviewPager=(ViewPager)view.findViewById(R.id.vp_content);
		 //底部的radiogroup
		 rg_group=(RadioGroup)view.findViewById(R.id.rg_group);
	
		return view;
		
	}

	@Override
	public void initData() {
		list=new ArrayList<BasePager>();
		//添加3个页面
		list.add(new SelectPager(mActivity));
		list.add(new NearPager(mActivity));
		list.add(new BuslinePager(mActivity));
		
		
		//给viewpager添加适配器
		mviewPager.setAdapter(new ContentAdapter());
		
		//给radioGroup设置监听事件--底栏标签切换
				rg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					//点击到某个radiobutton
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						//Radiobutton的id
						switch (checkedId) {
						case R.id.rb_select:
							//定位到第一个界面--搜索路线
							mviewPager.setCurrentItem(0, false);//flase去除滑动动画
							break;
						case R.id.rb_near:
							//附近站点
							mviewPager.setCurrentItem(1, false);//是否具有滑动动画
							break;
						case R.id.rb_busline:
							//公交线路
							mviewPager.setCurrentItem(2, false);//是否具有滑动动画
							break;
					
						}
					}
				});
				
		          
	}
/**
 * 给viewpager添加适配器
 * @author ZYX
 *
 */
	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view==obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		BasePager pager=list.get(position);
		View view=pager.mRootview;
		container.addView(view);//放入缓存里面
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		  
			 container.removeView((View)object);
		}
		
	}

}
