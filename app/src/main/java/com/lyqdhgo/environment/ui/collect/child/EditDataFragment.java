package com.lyqdhgo.environment.ui.collect.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyqdhgo.environment.App;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.adapter.DividerItemDecoration;
import com.lyqdhgo.environment.adapter.ResultAdapter;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.entity.Data;
import com.lyqdhgo.environment.entity.EmimsMonitorResult;
import com.lyqdhgo.environment.greendao.gen.EmimsMonitorResultDao;
import com.lyqdhgo.environment.util.ToastUtils;
import com.lyqdhgo.environment.weight.WaterRipplesView;

import java.util.List;

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
    EditText etID;
    @BindView(R.id.et_test2)
    EditText etType;
    @BindView(R.id.et_test3)
    EditText etUser;
    @BindView(R.id.et_test4)
    EditText etMin;
    @BindView(R.id.et_test5)
    EditText etMax;
    @BindView(R.id.et_test6)
    EditText etMark;
    @BindView(R.id.recycle_result)
    RecyclerView recyclerView;
    @BindView(R.id.but_insert)
    Button insert;
    @BindView(R.id.but_delete)
    Button delete;
    @BindView(R.id.but_query_all)
    Button queryAll;

    private Long id;
    private String type;
    private String user;
    private String minValue;
    private String maxValue;
    private String reMark;

    private EmimsMonitorResult emimsResult;
    private EmimsMonitorResult updateEmimsResult;
    private EmimsMonitorResultDao emimsResultDao;

    private ResultAdapter adapter;

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

        emimsResultDao = App.getInstance().getDaoSession().getEmimsMonitorResultDao();

        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        //event
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData((long) 1);

            }
        });

        queryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EmimsMonitorResult> list = emimsResultDao.loadAll();
                adapter.setStudents(list);
            }
        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                wave.breath();
//                //rleam
//                submitData();
//            }
//        });
//
//        query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<Data> list = new ArrayList<Data>();
//                list = queryAllDog();
//                for (int i = 0; i <list.size() ; i++) {
//                    Log.i("TAG","This Id is "+list.get(i).getId()+"\n"+list.get(i).getOneData()+"\n"+list.get(i).getTwoData());
//                }
//            }
//        });

    }

//    private void submitData() {
//        Realm realm = Realm.getDefaultInstance();
//
//        realm.beginTransaction();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String idTime = formatter.format(new Date(System.currentTimeMillis()));
//        Data data = realm.createObject(Data.class, idTime); // Create a new object
////        data.setId(idTime);
//        data.setOneData(Data1.getText().toString().trim());
//        data.setTwoData(Data2.getText().toString().trim());
//        realm.commitTransaction();
//        ToastUtils.showLongToast("Submit Finished");
//
//        // test GreenDao
////        dao.insert(new User((long) 123,"qdh","23","男","5000"));
//    }

    private List<Data> queryAllDog() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<Data> datas = mRealm.where(Data.class).findAll();
        return mRealm.copyFromRealm(datas);
    }

    // insert
    private void insertData() {
        emimsResult = new EmimsMonitorResult(Long.parseLong(etID.getText().toString().trim()), "", "", "", "", etType.getText().toString().trim(),
                "", "", "", "", "", etMax.getText().toString().trim(), etMin.getText().toString().trim(), "", null,
                etUser.getText().toString().trim(), null, "", etMark.getText().toString().trim());
        long id = emimsResultDao.insert(emimsResult);

        // 更新adapter
        EmimsMonitorResult result = emimsResultDao.load(id);
        adapter.addStudent(result);
        ToastUtils.showLongToast("插入数据");
    }

    // delete
    private void deleteData(Long id) {
        emimsResultDao.deleteByKey(id);
        ToastUtils.showLongToast("删除数据");
    }

    // update
    private void updateData(Long i) {
        updateEmimsResult = new EmimsMonitorResult();
        emimsResultDao.update(updateEmimsResult);
        ToastUtils.showLongToast("更新数据");
    }

    // select
    private void queryData() {
        List<EmimsMonitorResult> users = emimsResultDao.loadAll();
        // 清空列表,更新Adapter
        ToastUtils.showLongToast("查询全部数据");
    }
}
