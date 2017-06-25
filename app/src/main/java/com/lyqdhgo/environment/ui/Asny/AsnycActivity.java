package com.lyqdhgo.environment.ui.Asny;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseActivity;
import com.lyqdhgo.environment.entity.Result;
import com.lyqdhgo.environment.entity.UerService;
import com.lyqdhgo.environment.entity.UserVo;
import com.lyqdhgo.environment.weight.CBProgressBar;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                Log.i(TAG, "Click");
                //同步数据
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.100:8080/emims/a/base/sync/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UerService users = retrofit.create(UerService.class);
                Call<Result> call = users.listRepos("234");
                call.enqueue(new Callback<Result>() {

                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        List<UserVo> list = response.body().getObj();
                        Log.i(TAG, "response=" + list.toString());
                        for (int i = 0; i < list.size(); i++) {
                            Log.i(TAG, "name" + list.get(i).getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.i(TAG, "onFailure=" + t.getMessage());

                    }
                });
//                Response<UserVo> bodyResponse = null;
//                try {
//                    bodyResponse = call.execute();
//                    String body = bodyResponse.body().toString();
//                    Log.i(TAG, "body=" + body);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                EasyHttp.post("sync/syncUser.json")
//                        .baseUrl("h
// ttp://192.168.0.100:8080/emims/a/base/")
//                        .readTimeOut(30 * 1000)
//                        .writeTimeOut(30 * 1000)
//                        .connectTimeout(30 * 1000)
//                        .timeStamp(true)
//                        .execute(new SimpleCallBack<UserVo>() {
//                                     @Override
//                                     public void onError(ApiException e) {
//                                         Log.i(TAG, "Error");
//                                     }
//
//                                     @Override
//                                     public void onSuccess(UserVo response) {
//                                         response.toString();
//                                     }
//                                 }
//                        );
            }
        });
//        clickDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isDownloading) {
//                    stop = false;
//                    isDownloading = true;
//                    clickDown.setText("停止");
//                    downloading(downLoad);
//                } else {
//                    isDownloading = false;
//                    stop = true;
//                    clickDown.setText("下载");
//                }
//            }
//        });
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
