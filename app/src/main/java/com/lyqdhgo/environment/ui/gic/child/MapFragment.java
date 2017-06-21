package com.lyqdhgo.environment.ui.gic.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/5/1.
 */

public class MapFragment extends BaseFragment {

    public static MapFragment newInstance() {
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        mapFragment.setArguments(args);
        return mapFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initEventAndData() {

    }
}
