package com.lyqdhgo.environment.ui.set;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.ui.collect.child.EditDataFragment;

/**
 * Created by QiDeHong on 2017/6/22.
 */

public class EmimsResultActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_emims_result;
    }

    @Override
    protected void initEventAndData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, EditDataFragment.newInstance(""));
        transaction.commit();
    }
}
