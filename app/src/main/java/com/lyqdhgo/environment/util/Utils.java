package com.lyqdhgo.environment.util;

import android.content.Context;

import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.lyqdhgo.environment.R;

import java.util.ArrayList;

/**
 * Created by QiDeHong on 2017/4/21.
 */

public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    /***
     * Map Marker gif
     * @return
     */
    public static ArrayList setMapMarkerLayer() {
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(BitmapDescriptorFactory
                .fromResource(R.drawable.a));
        giflist.add(BitmapDescriptorFactory
                .fromResource(R.drawable.b));
        giflist.add(BitmapDescriptorFactory
                .fromResource(R.drawable.c));
        giflist.add(BitmapDescriptorFactory
                .fromResource(R.drawable.d));
        return giflist;
    }

    public static MarkerOptions setDefaultMarkerOption(LatLng latLng, String title, String snippet) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(title).snippet(snippet);
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        return markerOption;
    }

    public static MarkerOptions setMarkerOption(LatLng latLng, String title, String snippet, int drawableId) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(title).snippet(snippet);
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory.fromResource(drawableId));
        return markerOption;
    }

    public static MarkerOptions setGifMarkerOption(LatLng latLng, String title, String snippet, int time) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.anchor(0.5f, 0.5f)
                .position(latLng).title(title).snippet(snippet).icons(setMapMarkerLayer())
                .draggable(true).period(time);
        return markerOption;
    }


    public static void addMarker() {

    }

    public static void addMarkerofGif() {

    }
}
