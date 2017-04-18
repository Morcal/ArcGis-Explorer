package com.lyqdhgo.environment.ui.doc.child;

import android.os.Bundle;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class DocDetailFragment extends BaseFragment {
    public static DocDetailFragment newInstance() {
        Bundle args = new Bundle();
        DocDetailFragment fragment = new DocDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_doc_detail;
    }

    @Override
    protected void initEventAndData() {

    }
}
