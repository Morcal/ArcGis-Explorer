package com.lyqdhgo.environment.ui.doc.child;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.adapter.DocListAdapter;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.weight.DividerDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class DocListFragment extends BaseFragment {

    private DocListAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;

    public static DocListFragment newInstance() {
        Bundle args = new Bundle();
        DocListFragment fragment = new DocListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_doc_list;
    }

    @Override
    protected void initEventAndData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_header, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);
        header.setBackgroundColor(0xff1874CD);

        // add driver
//        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
//                .setHeight(R.dimen.default_divider_height)
//                .setPadding(R.dimen.default_divider_padding)
//                .setColorResource(R.color.red_primary)
//                .build();
//        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        listData.clear();
                        for (int i = 0; i < 15; i++) {
                            listData.add("Document " + i + "  after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                listData.add("Document " + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("Document " + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            listData.add("Document " + i);
        }
        mAdapter = new DocListAdapter(listData);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }


}
