package com.lyqdhgo.environment;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.facebook.stetho.Stetho;
import com.lyqdhgo.environment.greendao.gen.DaoMaster;
import com.lyqdhgo.environment.greendao.gen.DaoSession;
import com.lyqdhgo.environment.util.Utils;
import com.zhouyou.http.EasyHttp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by cuieney on 17/2/21.
 */

public class App extends MultiDexApplication {

    //    private AppComponent appConponent;
    private static App app;
    private Set<Activity> allActivities;
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    // sql
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static App getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Utils.init(this);
        initAppComponent();
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles = new ArrayList(list1);

        // set GreenDao
        setDatabase();
        // 调试
        Stetho.initializeWithDefaults(this);
        // EasyHttp初始化
        EasyHttp.init(this);//默认初始化
        // Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name("myRealm.realm")
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(config);
//        AutoLayoutConifg.getInstance().useDeviceSize();
//        getScreenSize();

//        //内存泄漏检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();

//        CrashHandler.getInstance().initCrashHandler(this);
    }

    //    public AppComponent getAppComponent() {
//        return appConponent;
//    }
//
    private void initAppComponent() {
//        appConponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .retrofitModule(new RetrofitModule(this))
//                .build();
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }
}
