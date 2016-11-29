package com.zyx.taiyuanbus;




import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * spalsh界面--欢迎界面
 * @author ZYX
 * 
 */
public class SplashActivity extends Activity{
	private SharedPreferences sp;
	private ImageView iv_bus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		  //参数--用来判断时是否是第一次进入，若是第一次进入则显示向导界面，若不是第一次进入，直接进入主界面
        sp=getSharedPreferences("config", MODE_PRIVATE);
        setContentView(R.layout.activity_splash);//绑定欢迎界面
        
      //找控件
        initUI();
        
     //设置动画
        setAnimation();
        
   
      
		
	}
	/**
	 * 判断是否第一次进入
	 */
	private void interPage() {
		//进行跳转
		if(sp.getBoolean("first_Inter", true)){
			//参数里如果是true，第一次进入，进入向导界面
			Intent intent =new Intent(SplashActivity.this,GuideActivity.class);
			startActivity(intent);
			finish();
			
			
		}else{
			//参数是false,直接进入主界面
			Intent intent =new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
			
		}
		
	}
	/**
	 * 设置动画
	 */
	private void setAnimation() {
		//透明动画AlphaAnimation
    	AlphaAnimation aa=new AlphaAnimation(0.0f, 1.0f);//从无到有
    	aa.setDuration(3000);//动画持续时间
    	aa.setFillAfter(true);//动画结束后，动画停在结束位置
    
    	
    	//平移动画
    
       TranslateAnimation  ta= new TranslateAnimation(-900f,1200.0f,0.5f,0.5f); 
    	ta.setDuration(3000);
    	ta.setFillAfter(true);//动画结束后，动画停在结束位置
    	
    	//组合动画
    	AnimationSet set=new AnimationSet(true);
    	set.addAnimation(aa);
    	set.addAnimation(ta);
    	set.setFillAfter(true);
    	//执行动画
    	iv_bus.startAnimation(set);
    
    	 //动画结束监听
        set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			//动画结束后执行的操作
			@Override
			public void onAnimationEnd(Animation animation) {
				//进行跳转
		 interPage();
				
			}
		});
	}
	/**
	 * 找控件
	 */
	private void initUI() {
		//欢迎界面的马--动画
		 iv_bus=(ImageView)findViewById(R.id.iv_bus);
		
	}

}
