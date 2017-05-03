package com.lyqdhgo.environment.ui.gic.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lyqdhgo.environment.util.Utils;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/5/1.
 */

public class MapFragment extends BaseFragment implements AMap.OnMarkerClickListener {

    public static final LatLng BEIJING = new LatLng(39.90403, 116.407525);// 北京市经纬度
    public static final LatLng ZHENGZHOU = new LatLng(34.7466, 113.625367);// 郑州市经纬度
    private LatLng latlng = new LatLng(36.061, 103.834);

    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    private Marker marker;
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

        markerOption.position(BEIJING);
        markerOption.title("北京市").snippet("北京市：39.90403, 116.407525");
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.d));
        marker = aMap.addMarker(markerOption);


        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(ZHENGZHOU).title("郑州市").icons(Utils.setMapMarkerLayer())
                .draggable(true).period(1));

        drawMarkers();// 添加10个带有系统默认icon的marker
    }

    private void drawMarkers() {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title("好好学习")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));
        marker.showInfoWindow();// 设置默认显示一个infowinfow
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
//            jumpPoint(marker);
        }
        Toast.makeText(getActivity(), "您点击了Marker"+marker.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }
}
