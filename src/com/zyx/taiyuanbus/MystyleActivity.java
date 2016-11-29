package com.zyx.taiyuanbus;


import java.util.ArrayList;
import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 我的个性界面
 * @author Administrator
 *
 */
public class MystyleActivity extends Activity {
	private ListView lv_list;
	private String textArray[];
	
	
	
	private List<ImageView> list;//装图片的list集合
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_style);
		
		initUI();//初始化控件
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		initData();//初始化数据--放在resume里面确保每次都是最新的数据
		
	}
	
	
	
	
	/**
	 * 初始化数据
	 */
private void initData() {
	list=new ArrayList<ImageView>();
	
	ImageView img1=new ImageView(this);
	img1.setBackgroundResource(R.drawable.list_icon1);
	list.add(img1);
	
	ImageView img2=new ImageView(this);
	img2.setBackgroundResource(R.drawable.list_icon2);
	list.add(img2);
	
	ImageView img3=new ImageView(this);
	img3.setBackgroundResource(R.drawable.list_icon3);
	list.add(img3);
	
	ImageView img4=new ImageView(this);
	img4.setBackgroundResource(R.drawable.list_icon4);
	list.add(img4);
		
	//文字内容
	 textArray=new String[]{"阔老爷","贵妇人","俊小伙","俏姑娘"};
	
	
	lv_list.setAdapter(new MyAdapter());
		
	}
/**
 * 给listview添加适配器
 * @author Administrator
 *
 */
class MyAdapter extends BaseAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;
		ViewHolder viewHolder;
		if(convertView!=null){
			view=convertView;
			//从view对象中得到控件的容器
			viewHolder=(ViewHolder)view.getTag();
		}else{
			view=View.inflate(MystyleActivity.this, R.layout.list_item, null);
			//创建控件的容器
			viewHolder=new ViewHolder();
			//把控件存放在容器中
			viewHolder.iv_icon=(ImageView)view.findViewById(R.id.iv_icon);//头像
			viewHolder.tv_text=(TextView)view.findViewById(R.id.tv_text);//文本
			
			    //将容器和view对象绑定在一起
			view.setTag(viewHolder);
		}
		//显示头像跟文本
		
		  ImageView iv_img=list.get(position);//获取到图片，放在控件上面
		  
		//  viewHolder.iv_icon.setImage
		 
		  
		  viewHolder.tv_text.setText(textArray[position]);
		
		return view;
	}
	
	/**
	 * 存放的容器
	 */
	class ViewHolder{
		 TextView tv_text;//文字内容
		 ImageView  iv_icon;//头像
		  
		
		
	}
	
	
}

/**
 * 找控件
 */
	private void initUI() {
      lv_list=(ListView)findViewById(R.id.lv_list);
		
	}
	//返回按钮的点击事件
	public void click(View v){
		finish();//把当前页面finish
	}

}
