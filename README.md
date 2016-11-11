# taiyuanBus
太原实时公交项目

         自己最近想做一个关于太原实时公交的项目，由于公交公司的数据接口，不是我能获取到的，但还是想做做这个项目，
         
         初步构想该项目主要分为司机端跟顾客端两个客户端，地图采用高德地图，本来想使用百度地图的，但是觉得现在高德地图人们用的多。
         
         太原实时公交，主要是通过客户端能够对自己想要知道的公交进行实时定位，离自己所在位置的距离，多长时间能够到达。我主要是想通过定位实现这个功能。
         
 在UI框架上想自己从头到尾，每个细节自己搭建，加入比较炫的特效，app里面的图标都采用扁平化设计，对美工又有一定的要求。
 
          今天晚上，主要是看了看高德地图的开发文档，拿到了sha1值  key值 把地图显示在界面上。
          
 // MainActivity代码如下
         
                      package com.zyx.taiyuanbus;

            import com.amap.api.maps.MapView;

            import android.os.Bundle;
            import android.app.Activity;
            import android.view.Menu;
            public class MainActivity extends Activity {
                MapView mMapView = null;
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                  super.onCreate(savedInstanceState);
                  setContentView(R.layout.activity_main);
                  //获取地图控件引用
                  mMapView = (MapView) findViewById(R.id.map);
                  //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
                  mMapView.onCreate(savedInstanceState);
                }
                @Override
                protected void onDestroy() {
                  super.onDestroy();
                  //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
                  mMapView.onDestroy();
                }
               @Override
               protected void onResume() {
                  super.onResume();
                  //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
                  mMapView.onResume();
                  }
               @Override
               protected void onPause() {
                  super.onPause();
                  //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
                  mMapView.onPause();
                  }
               @Override
               protected void onSaveInstanceState(Bundle outState) {
                  super.onSaveInstanceState(outState);
                  //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
                  mMapView.onSaveInstanceState(outState);
                }

              }
// BaseMapFragmentActivity  代码如下:         
                               package com.zyx.basic;

                  import android.os.Bundle;
                  import android.support.v4.app.FragmentActivity;

                  import com.amap.api.maps.AMap;
                  import com.amap.api.maps.SupportMapFragment;
                  import com.zyx.taiyuanbus.R;
                  import com.zyx.taiyuanbus.R.id;
                  import com.zyx.taiyuanbus.R.layout;
                  public class BaseMapFragmentActivity extends FragmentActivity {
                      private AMap mMap;

                      protected void onCreate(Bundle savedInstanceState) {
                          super.onCreate(savedInstanceState);
                          setContentView(R.layout.basemap_fragment_activity);
                          setUpMapIfNeeded();


                      }
                      @Override
                    protected void onResume() {
                      super.onResume();
                      setUpMapIfNeeded();
                    }

                    @Override
                    protected void onDestroy(){
                      super.onDestroy();
                    }

                    private void setUpMapIfNeeded() {
                      if (mMap == null) {
                        mMap = ((SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map)).getMap();
                      }
                    }
                      }

    
在布局文件中声明

              <com.amap.api.maps.MapView

              android:id="@+id/map"

              android:layout_width="match_parent"

              android:layout_height="match_parent"/>

BaseMapFragmentActivity布局文件
              <fragment 
              android:id="@+id/map" 
              android:layout_width="match_parent" 
              android:layout_height="match_parent" 
              class="com.amap.api.maps.SupportMapFragment">

          </fragment>

////////////////////////////////////////////////////////////
      其实以上没有自己的逻辑性代码，也就是初步了解了解高德地图，都是通过开发文档写的，很方便。































