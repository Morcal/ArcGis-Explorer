package com.lyqdhgo.environment.ui.statistic.child;

import android.os.Bundle;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class ChatFragment extends BaseFragment {

    public static ChatFragment newInstance() {
        Bundle args = new Bundle();
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic_chat;
    }

    @Override
    protected void initEventAndData() {

    }
}
