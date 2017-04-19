package com.lyqdhgo.environment.ui.collect.child;

import android.os.Bundle;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/4/19.
 */

public class PhotoFragment extends BaseFragment {

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_photo;
    }

    @Override
    protected void initEventAndData() {

    }
}
