package com.lyqdhgo.environment.ui.all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/6/17.
 */

public class AllActivity extends BaseActivity {
    @BindView(R.id.banner)
    MZBannerView mMZBanner;
    public static final int []RES = new int[]{R.mipmap.p0,R.mipmap.p1,R.mipmap.p2,R.mipmap.p3,R.mipmap.p4,R.mipmap.p5};

    @Override
    protected int getLayout() {
        return R.layout.activity_all;
    }

    @Override
    protected void initEventAndData() {
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(AllActivity.this, "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < RES.length; i++) {
            list.add(RES[i]);
        }
        mMZBanner.setIndicatorVisible(false);
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMZBanner.start();
    }
}
