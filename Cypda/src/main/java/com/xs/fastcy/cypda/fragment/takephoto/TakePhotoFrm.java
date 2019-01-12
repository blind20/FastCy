package com.xs.fastcy.cypda.fragment.takephoto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.entity.VehPhoto;
import com.xs.fastcy.cypda.fragment.cyxm.VehCyxmsFrm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TakePhotoFrm extends LatteDelegate {

    private VehCheckInfo mVehCheckInfo=null;
    private TakePhotoGridAdapter mAdapter =null;
    private FastCyDbUtil fastdb ;

    @BindView(R2.id.tv_csxx_hint)
    AppCompatTextView mHint;

    @BindView(R2.id.rv_takephoto)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {return R.layout.frm_takephoto;}

    @OnClick(R2.id.tv_back)
    public void onClickBack(){ getSupportDelegate().pop(); }

    @OnClick(R2.id.tv_next)
    public void onClickNext(){
        if(checkForm()){
            VehCheckInfo vehCheckInfo = mVehCheckInfo;
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",vehCheckInfo);
            VehCyxmsFrm vehCyxmsFrm = new VehCyxmsFrm();
            vehCyxmsFrm.setArguments(bundle);
            getSupportDelegate().start(vehCyxmsFrm);
        }
    }




    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final Context con = getContext();
        fastdb = new FastCyDbUtil(con);
        mVehCheckInfo = (VehCheckInfo) getArguments().getSerializable("data");
        if(mVehCheckInfo !=null){
            List<VehPhoto> photos = getVehPhotos(mVehCheckInfo);
            GridLayoutManager manager = new GridLayoutManager(con, 3);
            mRecyclerView.setLayoutManager(manager);
            ArrayList<MultipleItemEntity> data = new TakePhotoDataConverter(photos).setContext(con).convert();
            mAdapter = new TakePhotoGridAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnItemTouchListener(new TakePhotoClickListener(this,mVehCheckInfo,mHint));
        }
    }

    private boolean checkForm(){
        String lsh = mVehCheckInfo.getLsh();
        List<VehPhoto> zp_left = fastdb.queryVehPhotoByLshAndZpzl(lsh,CyConstant.ZPZL_45LEFT);
        List<VehPhoto> zp_vin = fastdb.queryVehPhotoByLshAndZpzl(lsh,CyConstant.ZPZL_VIN);
        if(zp_left==null  || zp_left.size()==0 ){
            ToastUtils.showLong("车辆左前45度没拍照上传成功，请检查或者重新拍摄");
            return false;
        }
        if(zp_vin == null || zp_vin.size()==0){
            ToastUtils.showLong("车辆识别代号没拍照上传成功，请检查或者重新拍摄");
            return false;
        }
        return true;
    }

    private List<VehPhoto> getVehPhotos(VehCheckInfo vehCheckInfo){
        return fastdb.queryVehPhotoByLsh(vehCheckInfo.getLsh());
    }

    @Override
    public void post(Runnable runnable) {}


}
