package com.lyqdhgo.environment.ui.collect.child;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.entity.Data;
import com.lyqdhgo.environment.util.ToastUtils;
import com.lyqdhgo.environment.weight.WaterRipplesView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by QiDeHong on 2017/4/18.
 */

public class EditDataFragment extends BaseFragment {
    private static final String MEASUREPOINT = "MEASUREPOINT";
    @BindView(R.id.wave)
    WaterRipplesView wave;
    @BindView(R.id.but_breath)
    Button submit;
    @BindView(R.id.but_query)
    Button query;
    @BindView(R.id.tv_measure_point)
    TextView measurePoint;
    @BindView(R.id.et_test1)
    EditText Data1;
    @BindView(R.id.et_test2)
    EditText Data2;

    public static EditDataFragment newInstance(String measure) {
        Bundle args = new Bundle();
        EditDataFragment fragment = new EditDataFragment();
        args.putString(MEASUREPOINT, measure);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_data;
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        measurePoint.setText(bundle.getString(MEASUREPOINT));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wave.breath();
                //rleam
                submitData();
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Data> list = new ArrayList<Data>();
                list = queryAllDog();
                for (int i = 0; i <list.size() ; i++) {
                    Log.i("TAG","This Id is "+list.get(i).getId()+"\n"+list.get(i).getOneData()+"\n"+list.get(i).getTwoData());
                }
            }
        });

    }

    private void submitData() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String idTime = formatter.format(new Date(System.currentTimeMillis()));
        Data data = realm.createObject(Data.class, idTime); // Create a new object
//        data.setId(idTime);
        data.setOneData(Data1.getText().toString().trim());
        data.setTwoData(Data2.getText().toString().trim());
        realm.commitTransaction();
        ToastUtils.showLongToast("Submit Finished");
    }

    public List<Data> queryAllDog() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<Data> datas = mRealm.where(Data.class).findAll();
        return mRealm.copyFromRealm(datas);
    }
}
