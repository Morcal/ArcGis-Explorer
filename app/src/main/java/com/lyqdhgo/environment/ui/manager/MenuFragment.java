package com.lyqdhgo.environment.ui.manager;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lyqdhgo.environment.R;

/**
 * Created by QiDeHong on 2017/6/16.
 */

public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView menuList;

    private ArrayAdapter<String> adapter;

    private String[] menuItems = {"First", "Second"};

    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, menuItems);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_menu, container, false);
        menuList = (ListView) view.findViewById(R.id.lv_menu);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.details_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
        if (isTwoPane) {
            Fragment fragment = null;
            if (index == 0) {
                fragment = new FirstFragment();
            } else if (index == 1) {
                fragment = new SecondFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.details_layout, fragment).commit();
        }
    }

}
