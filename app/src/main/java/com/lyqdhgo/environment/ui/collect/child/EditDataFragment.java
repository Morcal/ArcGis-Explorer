package com.lyqdhgo.environment.ui.collect.child;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.weight.WaterRipplesView;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class EditDataFragment extends BaseFragment {
    @BindView(R.id.wave)
    WaterRipplesView wave;
    @BindView(R.id.but_breath)
    Button breath;

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
        breath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wave.breath();
            }
        });
    }
}
