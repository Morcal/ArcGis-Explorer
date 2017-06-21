package com.lyqdhgo.environment.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.ui.all.AllActivity;
import com.lyqdhgo.environment.ui.collect.EnvironCheckActivity;
import com.lyqdhgo.environment.ui.gic.MapActivity;
import com.lyqdhgo.environment.ui.manager.ManagerActivity;
import com.lyqdhgo.environment.util.ToastUtils;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/6/5.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    @BindView(R.id.tv_project_name)
    TextView projectName;
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
    protected void initEventAndData() {
        init();
        prAsync.setOnClickListener(this);
        prlCollect.setOnClickListener(this);
        prlMansger.setOnClickListener(this);
        prlAnalysis.setOnClickListener(this);
        prlSet.setOnClickListener(this);

        prlAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, AllActivity.class));
            }
        });
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
                startActivity(new Intent(WelcomeActivity.this, EnvironCheckActivity.class));
                break;
            case R.id.prl_collect:
                startActivity(new Intent(WelcomeActivity.this, MapActivity.class));
                break;
            case R.id.prl_manager:
                startActivity(new Intent(WelcomeActivity.this, ManagerActivity.class));
                break;
            case R.id.all:
                ToastUtils.showLongToast("综合服务");


                break;
            case R.id.prl_analysis:
                break;
            case R.id.prl_set:
                break;
            default:
                break;
        }
    }
}
