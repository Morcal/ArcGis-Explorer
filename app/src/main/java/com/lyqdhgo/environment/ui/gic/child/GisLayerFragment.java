package com.lyqdhgo.environment.ui.gic.child;

import android.os.Bundle;

import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class GisLayerFragment extends BaseFragment {

//    @BindView(R.id.mapView)
//    MapView mMapView;

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
    protected void initEventAndData() {
//        setupMap();
//        addTrailheadsLayer();
    }
//
//    private void setupMap() {
//        if (mMapView != null) {
//            Basemap.Type basemapType = Basemap.Type.IMAGERY_WITH_LABELS_VECTOR;
//            double latitude = 34.05293;
//            double longitude = -118.24368;
//            int levelOfDetail = 11;
//            ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
//            mMapView.setMap(map);
//        }
//    }
//
//    private void addTrailheadsLayer() {
//        String url = "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trailheads/FeatureServer/";
//        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(url);
//        FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);
//        ArcGISMap map = mMapView.getMap();
//        map.getOperationalLayers().add(featureLayer);
//    }
}
