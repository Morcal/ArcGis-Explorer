package com.lyqdhgo.environment.ui.task.child;

import android.os.Bundle;

import com.lyqdhgo.environment.common.base.BaseMainFragment;

/**
 * Created by QiDeHong on 2017/4/21.
 */

public class ToDoDetailFragment extends BaseMainFragment {

    public static ToDoDetailFragment newInstance() {
        Bundle args = new Bundle();
        ToDoDetailFragment fragment = new ToDoDetailFragment();
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
}
