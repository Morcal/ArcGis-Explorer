package com.lyqdhgo.environment.ui.gic;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.clans.fab.FloatingActionButton;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.adapter.NormalRecyclerViewAdapter;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.util.Constants;
import com.lyqdhgo.environment.util.PoiOverlay;
import com.lyqdhgo.environment.util.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/6/10.
 */

public class MapActivity extends BaseActivity implements AMap.OnMyLocationChangeListener, PoiSearch.OnPoiSearchListener {
    private static final String TAG = MapActivity.class.getSimpleName();
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.lv_task)
    RecyclerView recyclerView;
    @BindView(R.id.fab_search)
    FloatingActionButton fabSearch;
    @BindView(R.id.fab_location)
    FloatingActionButton fabLocation;
    @BindView(R.id.fab_model)
    FloatingActionButton fabModel;
    @BindView(R.id.fab_center)
    FloatingActionButton fabCenter;
    @BindView(R.id.search_bar)
    LinearLayout searchBar;
    @BindView(R.id.search_edit)
    EditText searchEdit;

    private AMap aMap;
    private int count = 0;
    private int currentPage = 0;
    private String keyWord = "";
    private boolean isShowSearch = true;
    private MyLocationStyle locationStyle;
    private NormalRecyclerViewAdapter adapter;

    private PoiResult poiResult;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private ProgressDialog progDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    protected void initEventAndData() {
        initMap();
        setMarks();
        setRecycleView();

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearch();
            }
        });
        fabModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                switch (count % 5) {
                    case 0:
                        break;
                    case 1:
                        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
                        break;
                    case 2:
                        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                        break;
                    case 3:
                        aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图模式
                        break;
                    case 4:
                        aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航地图模式
                        break;
                    default:
                        break;
                }
            }
        });
        fabLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation();
            }
        });
        fabCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCamera(
                        CameraUpdateFactory.newCameraPosition(new CameraPosition(
                                Constants.ZHONGGUANCUN, 18, 30, 30)));
                aMap.clear();
                aMap.addMarker(new MarkerOptions().position(Constants.ZHONGGUANCUN)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        });
        adapter.setOnItemClickListener(new NormalRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                ToastUtils.showLongToast("position->" + position + "_" + textView.getText().toString().trim());
            }
        });
    }

    private void showSearch() {
        if (isShowSearch) {
            YoYo.with(Techniques.FadeInLeft)
                    .duration(700)
                    .repeat(1)
                    .playOn(searchBar);
            searchBar.setVisibility(View.VISIBLE);
            doSearchQuery();
            isShowSearch = false;
        } else {
            YoYo.with(Techniques.FadeOutRight)
                    .duration(700)
                    .repeat(1)
                    .playOn(searchBar);
            searchBar.setVisibility(View.GONE);
            isShowSearch = true;
        }
    }

    private void setLocation() {
        locationStyle = new MyLocationStyle();
        locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        locationStyle.strokeColor(STROKE_COLOR);
        locationStyle.strokeWidth(5);
        locationStyle.radiusFillColor(FILL_COLOR);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationStyle(locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));
    }

    private void setRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NormalRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void changeCamera(CameraUpdate update) {
        aMap.moveCamera(update);
    }

    protected void doSearchQuery() {
        showProgressDialog();
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", searchEdit.getText().toString());// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    private void setMarks() {
//        marker1 = aMap.addMarker(Utils.setMarkerOption(BEIJING, "北京市", "北京市：39.90403, 116.407525", R.drawable.marker_yj));
//        marker2 = aMap.addMarker(Utils.setDefaultMarkerOption(LATLNG, "好好学习", ""));
//        marker2.showInfoWindow();// 设置默认显示一个infowinfow
//        aMap.addMarker(Utils.setGifMarkerOption(ZHENGZHOU, "郑州市", "", 1));
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
//        setMapCustomStyleFile(this);
        aMap.setOnMyLocationChangeListener(this);
    }

    private void setMapCustomStyleFile(Context context) {
        String styleName = "style_json.json";
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        aMap.setCustomMapStylePath(filePath + "/" + styleName);
        aMap.showMapText(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if (location != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");
            }
        } else {
            Log.e("amap", "定位失败");
        }
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dissmissProgressDialog();// 隐藏对话框
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    if (poiItems != null && poiItems.size() > 0) {
                        aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtils.showLongToast("查无结果");
                    }
                }
            } else {
                ToastUtils.showLongToast("查无结果");

            }
        } else {
            ToastUtils.showLongToast("查无结果");
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtils.showLongToast(infomation);

    }

    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyWord);
        progDialog.show();
    }

    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
