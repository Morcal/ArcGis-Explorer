package com.lyqdhgo.environment.ui.collect.child;

import android.os.Bundle;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class EditDataFragment extends BaseFragment {

    public static EditDataFragment newInstance() {
        Bundle args = new Bundle();
        EditDataFragment fragment = new EditDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_data;
    }

    @Override
    protected void initEventAndData() {

    }
}
