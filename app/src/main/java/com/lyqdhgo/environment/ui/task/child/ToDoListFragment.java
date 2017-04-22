package com.lyqdhgo.environment.ui.task.child;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Fade;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.lyqdhgo.environment.R;
import com.lyqdhgo.environment.adapter.ItemDragAdapter;
import com.lyqdhgo.environment.common.base.BaseFragment;
import com.lyqdhgo.environment.common.helper.DetailTransition;
import com.lyqdhgo.environment.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by QiDeHong on 2017/4/17.
 */

public class ToDoListFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    private static final String TAG = ToDoListFragment.class.getSimpleName();
    private List<String> mData;
    private ItemDragAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    public static ToDoListFragment newInstance() {
        Bundle args = new Bundle();
        ToDoListFragment fragment = new ToDoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todolist;
    }

    @Override
    protected void initEventAndData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mData = generateData(50);
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag start");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }
        };
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "view swiped start: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View reset: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View Swiped: " + pos);
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(getActivity(), R.color.color_light_blue));
//                canvas.drawText("Just some text", 0, 40, paint);
            }
        };

        mAdapter = new ItemDragAdapter(mData);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
        mAdapter.enableDragItem(mItemTouchHelper);
        mAdapter.setOnItemDragListener(listener);
//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));

        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                ToastUtils.showShortToast("点击了" + position);
//            }
//        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShortToast("点击了" + position);
                ToDoDetailFragment fragment = ToDoDetailFragment.newInstance(mAdapter.getItem(position));

                // 这里是使用SharedElement的用例
                // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    setExitTransition(new Fade());
                    fragment.setEnterTransition(new Fade());
                    fragment.setSharedElementReturnTransition(new DetailTransition());
                    fragment.setSharedElementEnterTransition(new DetailTransition());

                    // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                    // 25.1.0+的support包，SharedElement正常
                    fragment.transaction()
//                            .addSharedElement(adapter.getViewByPosition(position,view.getId()), getString(R.string.image_transition))
//                            .addSharedElement(((FirstHomeAdapter.VH) vh).tvTitle, "tv")
                            .commit();
                }
                start(fragment);
            }
        });
    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("Task " + i);
        }
        return data;
    }
}
