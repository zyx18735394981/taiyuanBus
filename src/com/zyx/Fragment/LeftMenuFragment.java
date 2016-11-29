package com.zyx.Fragment;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.zyx.map.OfflineMapActivity;
import com.zyx.taiyuanbus.MainActivity;
import com.zyx.taiyuanbus.MystyleActivity;
import com.zyx.taiyuanbus.R;


/**
 * 侧边栏的fragment
 * @author Administrator
 *
 */
public class LeftMenuFragment extends BaseFragment {
	@ViewInject(R.id.ll_offline_map)
	private LinearLayout ll_offline_map;//离线地图
	
	@ViewInject(R.id.ll_shared)
	private LinearLayout ll_shared;//分享
	
	@ViewInject(R.id.iv_icon)
	private ImageView iv_icon;//积分的按钮
	
	

//初始化布局 覆写父类的方法
	@Override
	public View initView() {
		//加载侧边栏布局
		View view=View.inflate(mActivity, R.layout.fragment_left_menu, null);
	
		//用注入的方式去赋值
		ViewUtils.inject(this,view);//用注解的方法去赋值，注入view和事件
		return view;
	}
//实现父类方法  初始化数据
	@Override
	public void initData() {
		//离线地图
		offline_map();
		//推荐给朋友
		sharedFriend();
		//我的个性界面 --点击事件
		iconClick();
		
	}
	
	/**
	 * 离线地图
	 */
	public void offline_map(){
	   ll_offline_map.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//加载离线地图到内容界面
			
			Intent intent=new Intent(mActivity,OfflineMapActivity.class);
			startActivity(intent);
			
			
		}
	});
		
	}

	
	
	
	/**
	 * 推荐给朋友
	 */
	public void sharedFriend(){
		ll_shared.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//点击开始分享
				 ShareSDK.initSDK(mActivity);
				 OnekeyShare oks = new OnekeyShare();
				 //关闭sso授权
				 oks.disableSSOWhenAuthorize(); 

				// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
				 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
				 oks.setTitle("快来下载太原实时公交");
				 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
				 oks.setTitleUrl("http://sharesdk.cn");
				 // text是分享文本，所有平台都需要这个字段
				 oks.setText("实时监测公交的位置,让你出行更加方便！");
				 //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
				 oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
				 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
				 //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
				 // url仅在微信（包括好友和朋友圈）中使用
				 oks.setUrl("http://sharesdk.cn");
				 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
				 oks.setComment("实时监测公交的位置,让你出行更加方便！");
				 // site是分享此内容的网站名称，仅在QQ空间使用
				 oks.setSite("ShareSDK");
				 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
				 oks.setSiteUrl("http://sharesdk.cn");

				// 启动分享GUI
				 oks.show(mActivity);
				
			}
		});
		
	}
	
	/**
	 * 收起侧边栏
	 */
	protected void toggle() {
		//拿到侧边栏所依赖的activity
		MainActivity myActivity=(MainActivity)mActivity;
		//得到slidingMenu对象
		SlidingMenu slidingMenu=myActivity.getSlidingMenu();
		//调用toggle方法
		slidingMenu.toggle();//如果当前是开，调用之后就关了
		
	}
	
/**
 * 给头像设置点击事件
 */
	public void iconClick(){
		iv_icon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//点击图片，进入我的个性界面
				Intent intent=new Intent(mActivity,MystyleActivity.class);
			    startActivity(intent);
			
			}
		});
	}
	

}