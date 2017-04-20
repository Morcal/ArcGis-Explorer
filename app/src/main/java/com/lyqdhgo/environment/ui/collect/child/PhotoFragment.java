package com.lyqdhgo.environment.ui.collect.child;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.PreviewActivity;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/19.
 */

public class PhotoFragment extends BaseFragment {
    private static final int REQUEST_CAMERA_PERMISSIONS = 931;
    private static final int REQUEST_PREVIEW_CODE = 1001;
    public static final String FRAGMENT_TAG = "camera";
    public CameraFragment cameraFragment;

    @BindView(R.id.record_button)
    Button takePhoto;

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_photo;
    }

    @Override
    protected void initEventAndData() {
        addCamera();
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
                    @Override
                    public void onVideoRecorded(String filePath) {

                    }

                    @Override
                    public void onPhotoTaken(byte[] bytes, String filePath) {
                        startActivity(PreviewActivity.newIntentPhoto(getActivity(), filePath));
                    }
                });
            }
        });
//        cameraFragment.setResultListener(new CameraFragmentResultListener() {
//
//            @Override
//            public void onVideoRecorded(String filePath) {
//
//            }
//
//            @Override
//            public void onPhotoTaken(byte[] bytes, String filePath) {
//                //called when the photo is taken and saved
//
//            }
//        });
    }

    private void addCamera() {
        //you can configure the fragment by the configuration builder
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
                    .commit();
        }
    }


}
