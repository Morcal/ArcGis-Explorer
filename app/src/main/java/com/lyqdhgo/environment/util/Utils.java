package com.lyqdhgo.environment.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
//    public static ArrayList setMapMarkerLayer() {
//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        giflist.add(BitmapDescriptorFactory
//                .fromResource(R.drawable.a));
//        giflist.add(BitmapDescriptorFactory
//                .fromResource(R.drawable.b));
//        giflist.add(BitmapDescriptorFactory
//                .fromResource(R.drawable.c));
//        giflist.add(BitmapDescriptorFactory
//                .fromResource(R.drawable.d));
//        return giflist;
//    }

//    public static MarkerOptions setDefaultMarkerOption(LatLng latLng, String title, String snippet) {
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.title(title).snippet(snippet);
//        markerOption.draggable(true);
//        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//        return markerOption;
//    }

//    public static MarkerOptions setMarkerOption(LatLng latLng, String title, String snippet, int drawableId) {
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.title(title).snippet(snippet);
//        markerOption.draggable(true);
//        markerOption.icon(BitmapDescriptorFactory.fromResource(drawableId));
//        return markerOption;
//    }
//
//    public static MarkerOptions setGifMarkerOption(LatLng latLng, String title, String snippet, int time) {
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.anchor(0.5f, 0.5f)
//                .position(latLng).title(title).snippet(snippet).icons(setMapMarkerLayer())
//                .draggable(true).period(time);
//        return markerOption;
//    }
    public static void addMarker() {

    }

    public static void addMarkerofGif() {

    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 小米手机设置darkMode
     */
    public static boolean setXiaomiDarkMode(Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkModeFlag, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 魅族手机设置darkMode
     */
    public static boolean setMeizuDarkMode(Activity activity) {
        boolean result = false;
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class
                    .getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            value |= bit;
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

}
