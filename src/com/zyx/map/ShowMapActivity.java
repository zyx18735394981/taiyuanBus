package com.zyx.map;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.zyx.taiyuanbus.R;
/**
 * 定位入口
 * @author Administrator
 *
 */
public class ShowMapActivity extends Activity {
	//显示地图
	public  MapView mMapView = null;
	
	 //定位
    public AMapLocationClient mLocationClient = null; 
	
  
    public AMapLocationClientOption mLocationOption = null;  
    private double lat;  
    private double lon; 

    private AMap aMap; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_map);
		//找控件
	    mMapView = (MapView)findViewById(R.id.map);
	    
	    mMapView.onCreate(savedInstanceState);
	    
        mLocationClient = new AMapLocationClient(getApplicationContext());  
        
        mLocationClient.setLocationListener(mLocationListener);  
  
        init();  
	   
	}

	

	@Override
	  protected void onDestroy() {
	    super.onDestroy();
	  
	    mMapView.onDestroy();
	    mLocationClient.onDestroy();//取消定位
	  }
	 @Override
	 protected void onResume() {
	    super.onResume();
	    
	    mMapView.onResume();
	    }
	 @Override
	 protected void onPause() {
	    super.onPause();
	   
	    mMapView.onPause();
	    }
	 
	 @Override
	protected void onStop() {
		super.onStop();
		 mLocationClient.stopLocation();//ֹͣ停止定位
	}
	 
	 
	 @Override
	 protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	  
	    mMapView.onSaveInstanceState(outState);
	  }
	     
	 
	 
	
	    public AMapLocationListener mLocationListener = new AMapLocationListener() {  
	        @Override  
	        public void onLocationChanged(AMapLocation amapLocation) {  
	            if (amapLocation != null) {  
	                if (amapLocation.getErrorCode() == 0) {  
	                   
	                    amapLocation.getLocationType();
	                    amapLocation.getLatitude();
	                    amapLocation.getLongitude();
	                    amapLocation.getAccuracy(); 
	                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                    Date date = new Date(amapLocation.getTime());  
	                    df.format(date);
	                    amapLocation.getAddress();
	                    amapLocation.getCountry();
	                    amapLocation.getProvince(); 
	                    amapLocation.getCity();
	                    amapLocation.getDistrict(); 
	                    amapLocation.getStreet();
	                    amapLocation.getStreetNum();  
	                    amapLocation.getCityCode();
	                    amapLocation.getAdCode();  
	                    amapLocation.getAoiName();
	                    lat = amapLocation.getLatitude();  
	                    lon = amapLocation.getLongitude();  
	                    Log.v("pcw","lat : "+lat+" lon : "+lon);  
	                   
	                    System.out.println(lat+"===="+lon);
	                   
	                    System.out.println( "Country : "+amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());
	  
	                  
	                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 19));  
	                    MarkerOptions markerOptions = new MarkerOptions();  
	                    markerOptions.position(new LatLng(lat, lon));  
	                    markerOptions.title(amapLocation.getCountry() + "," + amapLocation.getProvince() + "," + amapLocation.getCity() + "," + amapLocation.getDistrict());  
	                    markerOptions.visible(true); 
	                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.navi_map_gps_locked));  
	                    markerOptions.icon(bitmapDescriptor);  
	                    aMap.addMarker(markerOptions);  
	                } else {  
	                   
	                    Log.e("AmapError", "location Error, ErrCode:"  
	                            + amapLocation.getErrorCode() + ", errInfo:"  
	                            + amapLocation.getErrorInfo());  
	                    
	                    System.out.println(amapLocation.getErrorCode()+"=="+amapLocation.getErrorInfo());
	                }  
	            }  
	        }  
	    };

	    
	     
	    private void init() {  
	        if (aMap == null) {  
	            aMap = mMapView.getMap();  
	        }  
	  
	        setUpMap();  
	  
	    }


 
	    private void setUpMap() {  
	       
	        mLocationOption = new AMapLocationClientOption();  
	        
	        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);  
	       
	        mLocationOption.setNeedAddress(true);  
	     
	        mLocationOption.setOnceLocation(false);  
	        
	        mLocationOption.setWifiActiveScan(true);  
	       
	        mLocationOption.setMockEnable(false);  
	         
	        mLocationOption.setInterval(2000);  
	      
	        mLocationClient.setLocationOption(mLocationOption);  
	     
	        mLocationClient.startLocation();  
	    }  
	    
	  
	    
}
