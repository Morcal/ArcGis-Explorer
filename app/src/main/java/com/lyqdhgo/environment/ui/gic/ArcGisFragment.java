package com.lyqdhgo.environment.ui.gic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseMainFragment;
import com.lyqdhgo.environment.ui.gic.child.MapFragment;

/**
 * Created by QiDeHong on 2017/4/17.
 */

public class ArcGisFragment extends BaseMainFragment {

    public static ArcGisFragment newInstance() {

        Bundle args = new Bundle();

        ArcGisFragment fragment = new ArcGisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arcgis, container, false);
        return view;

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_first_container, GisLayerFragment.newInstance());
            loadRootFragment(R.id.fl_first_container, MapFragment.newInstance());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
