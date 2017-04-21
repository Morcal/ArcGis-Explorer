package com.lyqdhgo.environment.ui.gic.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class GisLayerFragment extends BaseFragment {
    private static final String TAG = GisLayerFragment.class.getSimpleName();
    private String strMapUrl = "http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer";
    private ArcGISTiledMapServiceLayer arcGISTiledMapServiceLayer = null;

    @BindView(R.id.map)
    MapView mapView;

    public static GisLayerFragment newInstance() {
        Bundle args = new Bundle();
        GisLayerFragment fragment = new GisLayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_arcgis_layer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initEventAndData() {
        arcGISTiledMapServiceLayer = new ArcGISTiledMapServiceLayer(strMapUrl);
        mapView.centerAndZoom(98.219116, 26.933038, 0.1f);
        mapView.setEsriLogoVisible(false);
        mapView.addLayer(arcGISTiledMapServiceLayer);
        Log.i(TAG, "initEventAndData");
    }

}
