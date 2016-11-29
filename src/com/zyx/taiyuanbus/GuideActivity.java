package com.zyx.taiyuanbus;



import java.util.ArrayList;
import java.util.List;




import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



/**
 * 导航界面--用户第一次进入的时候
 * @author ZYX
 *
 */
public class GuideActivity extends Activity{
	private SharedPreferences sp;
	private ViewPager vp_pager;
	private List<ImageView> list;//放欢迎页的界面
	private LinearLayout ll;
	private Button btn_go;
	private int mDis;//小圆点
	private ImageView iv_red;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 //参数--用来判断时是否是第一次进入，若是第一次进入则显示欢迎界面
		 //若不是第一次进入，直接进入主界面
	        sp=getSharedPreferences("config", MODE_PRIVATE);

	        setContentView(R.layout.activity_guide);//绑定欢迎界面
	        
	        //找控件viewpager
	         vp_pager=(ViewPager)findViewById(R.id.vp_pager);
	         
	         ll=(LinearLayout)findViewById(R.id.ll);
	         
	         btn_go=(Button)findViewById(R.id.btn_go);
	         
	         //小红点
	  	   iv_red=(ImageView)findViewById(R.id.iv_red);
	         
	         //初始化数据
	         initData();
	         
	       
	         
	        
	        
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		list=new ArrayList<ImageView>();
		//添加图片
		ImageView image1=new ImageView(this);
		image1.setBackgroundResource(R.drawable.splash1);
		list.add(image1);
		ImageView image2=new ImageView(this);
		image2.setBackgroundResource(R.drawable.splash2);
		list.add(image2);
		ImageView image3=new ImageView(this);
		image3.setBackgroundResource(R.drawable.sp3);
		list.add(image3);
		ImageView image4=new ImageView(this);
		image4.setBackgroundResource(R.drawable.splash4);
		list.add(image4);
		
		//初始化小白点--list集合里面有几张图片，就有几个小白点
		for(int j=0;j<list.size();j++){
			ImageView pointView=new ImageView(getApplicationContext());
			//设置图片是一个形状
			pointView.setImageResource(R.drawable.shap_point_gray);
		    //初始化布局参数--包裹内容
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           //不是第一个小白点
			if(j>0){
				//设置左边距
				params.leftMargin=10;
				
			}
			//把参数加入Imageview中
	     	pointView.setLayoutParams(params);
			
			ll.addView(pointView);//添加圆点
			
			
		}
		
		
		//给viewpager增加适配器
		vp_pager.setAdapter(new MyAdapter());
		
		//设置viewpager的滑动监听
		
				vp_pager.setOnPageChangeListener(new OnPageChangeListener() {
					
					//页面被选中的时候调用
					@Override
					public void onPageSelected(int position) {
						//如果滑到最后一页，把开始体验的按钮显示
						if(position==list.size()-1){
							//向导的最后一页
							btn_go.setVisibility(View.VISIBLE);//按钮显示可见
				
						}else{
							btn_go.setVisibility(View.INVISIBLE);//其他界面按钮不可见
						}
						
						
					}
					
					//当页面滑动过程中的回调--考虑小圆点的滑动
					@Override
					public void onPageScrolled(int position, float positionOffset,
							int positionOffsetPixels) {
						//当页面滑动过程中的回调
						//position 当前位置   
						//positionOffset 移动偏移百分比
						//更新小圆点距离
			       int red_dis=(int) (mDis *positionOffset)+position*mDis;
			     //回调，只有等滑动的时候才会调
			     //更新小红点的距离
			     //---red_dis====margin_left
			         
				RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)iv_red.getLayoutParams();
				//修改左边距	
				params.leftMargin=red_dis;
					//重新设置布局参数
					iv_red.setLayoutParams(params);
					}
					
					
					//页面状态发生改变
					@Override
					public void onPageScrollStateChanged(int state) {
						
					}
				});
				
				/**
				 * 自定义控件流程--measure测量-->layout确定位置-->draw(onCreate执行完才会走此流程)
				 * 
				 */
				//监听layout结束的事件，位置确定好在获取圆点
				iv_red.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
					
					@Override
					//移除监听 避免重复回调
					public void onGlobalLayout() {
						iv_red.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						//iv_red.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						//layout方法执行结束的回调
						//计算两个圆点的距离
						//移动距离=第二个圆点的left值-第一个圆点left值
						
					mDis=ll.getChildAt(1).getLeft()-ll.getChildAt(0).getLeft();
					
					System.out.println("圆点距离:"+mDis);
					}
				});
			}	
	
	//viewpager的适配器
	class MyAdapter extends PagerAdapter{

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
		     	ImageView view=list.get(position);
		     	
		     	container.addView(view);//把界面写入缓存
		     	
			    return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
	}
	
	/**
	 * 开始体验的按钮点击事件
	 * 
	 */
 public void click(View v){
	//把参数的first_enter改为false
	 //用sp对象找到edit方法
 	Editor edit=sp.edit();
 	//改变存储的boolean值
		edit.putBoolean("first_Inter", false);
		//提交
		edit.commit();
		

		
		//跳转主界面
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	 
 }
	
}















