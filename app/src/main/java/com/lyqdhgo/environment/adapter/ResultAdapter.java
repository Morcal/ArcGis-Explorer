package com.lyqdhgo.environment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.entity.EmimsMonitorResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiDeHong on 2017/6/5.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private List<EmimsMonitorResult> list = new ArrayList<>();

    public void addStudent(EmimsMonitorResult result) {
        list.add(result);
        notifyItemInserted(list.size() - 1);
    }

    public void setStudents(List<EmimsMonitorResult> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EmimsMonitorResult result = list.get(position);
        holder.id.setText(result.getId().toString());
        holder.type.setText(result.getMonitorType());
        holder.user.setText(result.getCreateUser());
        holder.minValue.setText(result.getStandardMinValue());
        holder.maxValue.setText(result.getStandardMaxValue());
        holder.remark.setText(result.getRemarks());

//        if(student.getTeacher() != null) {
//            holder.textTeacher.setText(student.getTeacher().getName());
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView type;
        private TextView user;
        private TextView minValue;
        private TextView maxValue;
        private TextView remark;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            user = (TextView) itemView.findViewById(R.id.tv_user);
            minValue = (TextView) itemView.findViewById(R.id.tv_min);
            maxValue = (TextView) itemView.findViewById(R.id.tv_max);
            remark = (TextView) itemView.findViewById(R.id.tv_remark);

        }
    }
}
