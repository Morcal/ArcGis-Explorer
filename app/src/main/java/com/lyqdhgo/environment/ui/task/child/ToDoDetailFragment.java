package com.lyqdhgo.environment.ui.task.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseBackFragment;
import com.lyqdhgo.environment.common.base.BaseMainFragment;

/**
 * Created by QiDeHong on 2017/4/21.
 */

public class ToDoDetailFragment extends BaseBackFragment {

    private static final String ARG_ITEM = "arg_item";
    private String mTask;

    public static ToDoDetailFragment newInstance(String task) {
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, task);
        ToDoDetailFragment fragment = new ToDoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = getArguments().getString(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

}
