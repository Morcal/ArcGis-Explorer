package com.lyqdhgo.environment.ui.doc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseMainFragment;
import com.lyqdhgo.environment.ui.doc.child.DocDetailFragment;

/**
 * Created by QiDeHong on 2017/4/17.
 */

public class DocFragment extends BaseMainFragment {

    public static DocFragment newInstance() {
        Bundle args = new Bundle();
        DocFragment fragment = new DocFragment();
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
        View view = inflater.inflate(R.layout.fragment_doc, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_four_container, DocDetailFragment.newInstance());
        }
    }
}
