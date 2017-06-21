package com.lyqdhgo.environment.ui.statistic;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.ui.statistic.child.ChatFragment;

/**
 * Created by QiDeHong on 2017/6/22.
 */

public class StatisticActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_statisic;
    }

    @Override
    protected void initEventAndData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, ChatFragment.newInstance());
        transaction.commit();
    }
}
