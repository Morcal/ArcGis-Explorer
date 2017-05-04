package com.lyqdhgo.environment.ui.gic.child;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.common.helper.DetailTransition;
import com.lyqdhgo.environment.ui.collect.child.EditDataFragment;
import com.lyqdhgo.environment.ui.task.child.ToDoDetailFragment;
import com.lyqdhgo.environment.util.ToastUtils;
import com.lyqdhgo.environment.util.Utils;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/5/1.
 */

public class MapFragment extends BaseFragment implements AMap.OnMarkerClickListener {

    public static final String BeiJing = "北京市";
    public static final String ZhengZhou = "郑州市";
    public static final LatLng BEIJING = new LatLng(39.90403, 116.407525);// 北京市经纬度
    public static final LatLng ZHENGZHOU = new LatLng(34.7466, 113.625367);// 郑州市经纬度

    private LatLng LATLNG = new LatLng(36.061, 103.834);

    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    private Marker marker1, marker2;
    private MarkerOptions markerOption;

    public static MapFragment newInstance() {
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        mapFragment.setArguments(args);
        return mapFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initEventAndData() {
        markerOption = new MarkerOptions();
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
//        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
//        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        addMarkersToMap();// 往地图上添加marker
    }

    private void addMarkersToMap() {
        marker1 = aMap.addMarker(Utils.setMarkerOption(BEIJING, "北京市", "北京市：39.90403, 116.407525", R.drawable.d));

        aMap.addMarker(Utils.setGifMarkerOption(ZHENGZHOU, "郑州市", "", 1));

        drawMarkers();
    }

    private void drawMarkers() {
        marker2 = aMap.addMarker(Utils.setDefaultMarkerOption(LATLNG, "好好学习", ""));
        marker2.showInfoWindow();// 设置默认显示一个infowinfow
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (aMap != null) {
//            switch (marker.getTitle()) {
//                case BeiJing:
//
//                    break;
//                case ZhengZhou:
//
//                    break;
//                default:
//                    break;
//            }
//            jumpPoint(marker);
        }
        startFragment(marker);
        return true;
    }

    public void startFragment(Marker marker) {
        Toast.makeText(getActivity(), "您点击了Marker" + marker.getTitle(), Toast.LENGTH_LONG).show();
        EditDataFragment fragment = EditDataFragment.newInstance(marker.getTitle());
        // 这里是使用SharedElement的用例
        // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            setExitTransition(new Fade());
            fragment.setEnterTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailTransition());
            fragment.setSharedElementEnterTransition(new DetailTransition());
            fragment.transaction().commit();
        }
        start(fragment);
    }

}
