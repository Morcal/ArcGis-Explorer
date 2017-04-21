package com.lyqdhgo.environment.common.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lyqdhgo.environment.R;

/**
 * Created by QiDeHong on 2017/4/21.
 */

public class BaseBackFragment extends BaseFragment {
    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }
}
