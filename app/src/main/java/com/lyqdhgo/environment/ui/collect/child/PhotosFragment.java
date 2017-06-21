package com.lyqdhgo.environment.ui.collect.child;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/5/16.
 */

public class PhotosFragment extends BaseFragment {
    private static final int REQUEST_CODE_CHOOSE = 23;

    @BindView(R.id.but_take_photo)
    Button takePhoto;
    @BindView(R.id.recycle_photo)
    RecyclerView recyclerView;
    private UriAdapter mAdapter;

    public static PhotosFragment newInstance() {
        Bundle args = new Bundle();
        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photos;
    }

    @Override
    protected void initEventAndData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter = new UriAdapter());
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.from(PhotosFragment.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .theme(R.style.Matisse_Zhihu)
                        .maxSelectable(9)
//                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
    }

    private class UriAdapter extends RecyclerView.Adapter<UriAdapter.UriViewHolder> {

        private List<Uri> mUris;

        void setData(List<Uri> uris) {
            mUris = uris;
            notifyDataSetChanged();
        }

        @Override
        public UriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new UriViewHolder(
                    (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.uri_item, parent, false));
        }


        @Override
        public void onBindViewHolder(UriViewHolder holder, int position) {
            Uri uri = mUris.get(position);
            Glide.with(getActivity())
                    .load(uri.toString())
                    .dontAnimate()
//                    .placeholder(R.drawable.loading_spinner)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mUris == null ? 0 : mUris.size();
        }

        class UriViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            UriViewHolder(ImageView uri) {
                super(uri);
                imageView = uri;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List list = Matisse.obtainResult(data);
            for (int i = 0; i < list.size(); i++) {
                Log.i("TAG", list.get(i).toString());
            }
            mAdapter.setData(Matisse.obtainResult(data));
        }
    }
}
