package com.unco.commonimageloader.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.unco.commonimageloader.R;
import com.unco.commonimageloader.baseadapter.RecyclerAdapter;
import com.unco.commonimageloader.main.httputil.HttpManager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/8/8 15:37
 * @Action :
 *
 * =================中康================
 */
public class MainActivity extends AppCompatActivity implements LRecyclerView.LScrollListener, RecyclerAdapter
        .OnItemClickListener {
    LRecyclerView mRecycleview;
    private int page = 1;
    private static final int pageSize = 10;
    private GirlAdapter mAdapter;
    private List<GirlBean> mList;
    private ArrayList<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleview = (LRecyclerView) findViewById(R.id.recycleview);
        initRecycleView();
        requesData();
    }


    private void initRecycleView() {
        mList = new ArrayList<>();
        mAdapter = new GirlAdapter(R.layout.item_girl, mList);
        mAdapter.setOnItemClickListener(this);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setAdapter(new LRecyclerViewAdapter(mAdapter));
        mRecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        mRecycleview.setLScrollListener(this);
        mRecycleview.setRefreshing(true);//进入就开启刷新
    }

    private void requesData() {
        HttpManager.getInstance().getGirls("福利", 10, page, new Subscriber<List<GirlBean>>() {
            @Override
            public void onCompleted() {
                mRecycleview.refreshComplete();
                RecyclerViewStateUtils.setFooterViewState(mRecycleview, LoadingFooter.State.Normal);
            }

            @Override
            public void onError(Throwable e) {//刷新失败
                mRecycleview.refreshComplete();
                if (page != 1) {

                    RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mRecycleview, pageSize,
                            LoadingFooter.State.NetWorkError,
                            errorListener);
                } else Toast.makeText(getApplicationContext(), "加载失败,稍后重试", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(List<GirlBean> girlBeen) {
                if (page == 1) {
                    mAdapter.clear();
                    mList.clear();
                }
                mList.addAll(girlBeen);
                mAdapter.addItems(girlBeen);
            }
        });
    }

    View.OnClickListener errorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mRecycleview, pageSize, LoadingFooter
                    .State.Loading, null);
            requesData();
        }
    };


    @Override
    public void onRefresh() {
        RecyclerViewStateUtils.setFooterViewState(mRecycleview, LoadingFooter.State.Normal);
        page = 1;
        requesData();
    }

    @Override
    public void onScrollUp() {

    }

    @Override
    public void onScrollDown() {

    }

    @Override
    public void onBottom() {
        RecyclerViewStateUtils.setFooterViewState(this, mRecycleview, pageSize, LoadingFooter.State.Loading, null);
        //加载更多
        page++;
        requesData();
    }

    @Override
    public void onScrolled(int i, int i1) {

    }

    @Override
    public void onScrollStateChanged(int i) {

    }

    @Override
    public void onItemClick(int position) {
      
    }
}  