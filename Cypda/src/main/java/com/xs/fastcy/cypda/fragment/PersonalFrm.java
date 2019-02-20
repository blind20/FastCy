package com.xs.fastcy.cypda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.User;
import com.xs.fastcy.cypda.fragment.list.SimpleListAdapter;
import com.xs.fastcy.cypda.fragment.list.SimpleListBean;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFrm extends BottomItemDelegate {


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.tv_username)
    TextView mUserName = null;


    @OnClick(R2.id.tv_logout)
    void onClickEvent(View view){
        switch (view.getId()){
            case R.id.tv_logout:
                getParentDelegate().getActivity().finish();
                break;
            default:
                break;
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.frm_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        User user = getUserInfo();
        if(user!=null){
            mUserName.setText(user.getUsername());
            setListView(user);
        }
    }

    private void setListView(User user){
        final List<SimpleListBean> datas = new ArrayList<>();
        String[] items_left = getContext().getResources().getStringArray(R.array.personal_info);
        for (int i = 0; i <items_left.length ; i++) {
            SimpleListBean bean = new SimpleListBean.Builder()
                    .setItemType(VehItemType.ITEM_SIMPLE_LIST_2)
                    .setText(items_left[i])
                    .build();
            switch (i){
                case 0:
                    bean.setItemValue(user.getYhxm());
                    break;
                case 1:
                    bean.setItemValue(user.getBmmc());
                    break;
                case 2:
                    bean.setItemValue(ToolUtil.getVersionName(getContext()));
                    break;
            }
            datas.add(bean);
        }
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        final SimpleListAdapter adapter = new SimpleListAdapter(datas);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    private User getUserInfo(){
        User user=new FastCyDbUtil().queryLoginUser();
        return user;
    }



    @Override
    public void post(Runnable runnable) {}
}
