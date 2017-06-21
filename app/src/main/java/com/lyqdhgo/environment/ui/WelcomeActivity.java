package com.lyqdhgo.environment.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.ui.Asny.AsnycActivity;
import com.lyqdhgo.environment.ui.all.ComServiceActivity;
import com.lyqdhgo.environment.ui.collect.EnvironCheckActivity;
import com.lyqdhgo.environment.ui.gic.MapActivity;
import com.lyqdhgo.environment.ui.set.EmimsResultActivity;
import com.lyqdhgo.environment.ui.statistic.StatisticActivity;
import com.lyqdhgo.environment.util.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/6/5.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    @BindView(R.id.tv_project_name)
    TextView projectName;
    @BindView(R.id.tv_user)
    TextView userName;
    @BindView(R.id.prl_async)
    RelativeLayout prAsync;
    @BindView(R.id.prl_collect)
    RelativeLayout prlCollect;
    @BindView(R.id.prl_manager)
    RelativeLayout prlMansger;
    @BindView(R.id.prl_all)
    RelativeLayout prlAll;
    @BindView(R.id.prl_analysis)
    RelativeLayout prlAnalysis;
    @BindView(R.id.prl_set)
    RelativeLayout prlSet;
    private Context cx;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEventAndData() {
        init();
        prAsync.setOnClickListener(this);
        prlCollect.setOnClickListener(this);
        prlMansger.setOnClickListener(this);
        prlAll.setOnClickListener(this);
        prlAnalysis.setOnClickListener(this);
        prlSet.setOnClickListener(this);

//        prlAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(WelcomeActivity.this, AllActivity.class));
//            }
//        });
    }

    private void init() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/HYXingKaiJ.ttf");
        projectName.setTypeface(typeFace);
        projectName.setText("夹岩水利枢纽工程环境管理信息平台\n终端系统");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prl_async:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, AsnycActivity.class));
                }
                break;
            case R.id.prl_collect:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MapActivity.class));
                }
                break;
            case R.id.prl_manager:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, EnvironCheckActivity.class));
                }
                break;
            case R.id.prl_all:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, ComServiceActivity.class));
                }
                break;
            case R.id.prl_analysis:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, StatisticActivity.class));
                }
                break;
            case R.id.prl_set:
                if (!Constants.isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, EmimsResultActivity.class));
                }
                break;
            default:
                break;
        }
    }

    //订阅LoginActivity发布的消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String msg) {
        Log.i(TAG, Thread.currentThread().getName() + "->MainThread->" + msg);
        userName.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
