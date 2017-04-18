package com.lyqdhgo.environment.ui.gic.child;

import android.os.Bundle;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class GisLayerFragment extends BaseFragment {

    public static GisLayerFragment newInstance() {
        Bundle args = new Bundle();
        GisLayerFragment fragment = new GisLayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_arcgis_layer;
    }

    @Override
    protected void initEventAndData() {

    }
}
