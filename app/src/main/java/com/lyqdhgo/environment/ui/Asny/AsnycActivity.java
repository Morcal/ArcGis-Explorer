package com.lyqdhgo.environment.ui.Asny;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.weight.CBProgressBar;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/6/21.
 */

public class AsnycActivity extends BaseActivity {
    private static final String TAG = AsnycActivity.class.getSimpleName();
    private static final int UPDATE_PROGRESS = 0;
    @BindView(R.id.cby_download)
    CBProgressBar downLoad;
    @BindView(R.id.tv_click)
    TextView clickDown;
    boolean isDownloading;
    boolean stop;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    downLoad.setProgress(msg.arg1);
                    if (msg.arg1 == 100) {
                        isDownloading = false;
                        clickDown.setText("同步完成");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_asnyc;
    }

    @Override
    protected void initEventAndData() {
        downLoad.setMax(100);
        clickDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDownloading) {
                    stop = false;
                    isDownloading = true;
                    clickDown.setText("停止");
                    downloading(downLoad);
                } else {
                    isDownloading = false;
                    stop = true;
                    clickDown.setText("下载");
                }
            }
        });
    }

    private void downloading(CBProgressBar cbProgress) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (!stop) {
                    if (progress >= 100) {
                        break;
                    }
                    Message msg = handler.obtainMessage();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress += 1;
                    msg.what = UPDATE_PROGRESS;
                    msg.arg1 = progress;
                    msg.sendToTarget();
                }
                progress = 0;
            }
        }).start();

    }
}
