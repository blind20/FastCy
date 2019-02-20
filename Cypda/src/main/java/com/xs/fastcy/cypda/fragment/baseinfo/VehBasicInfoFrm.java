package com.xs.fastcy.cypda.fragment.baseinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.fragment.RequestCodes;
import com.xs.fastcy.cypda.fragment.ggxx.GgxxSelectFrm;
import com.xs.fastcy.cypda.fragment.list.HgzOr01C21Frm;
import com.xs.fastcy.cypda.fragment.takephoto.TakePhotoFrm;
import com.xs.fastcy.cypda.util.BeanRefUtil;
import com.xs.fastcy.cypda.util.DeviceUtil;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class VehBasicInfoFrm extends LatteDelegate   {
    @BindView(R2.id.rv_veh_baseinfo)
    RecyclerView mRecyclerView = null;
    Context mContext;
    VehBasicInfoAdapter mAdapter;
    VehCheckInfo mVehCheckInfo;

    String mJSON=null;
    boolean mIsSchoolCar=false;//非校车false;校车true;默认flase非校车
    boolean mIsNewCar = true;//新车true;在用车flase
    boolean mIsLocalCity = true;
    String mGcjk = "A";
    String mGgbh;
    String mSfxny;
    String mXnyzl;
    String mHphmFull;

    @Override
    public Object setLayout() {
        return R.layout.frm_veh_baseinfo;
    }

    @BindView(R2.id.tv_djxx)
    TextView mTextViewDjxx;

    @OnClick({R2.id.tv_back,R2.id.ll_djxx,R2.id.ll_ggxx,R2.id.tv_next_cyxm})
    void onClickEvent(View view){
        switch (view.getId()){
            case R.id.tv_back:
                getSupportDelegate().pop();
                break;
            case R.id.ll_djxx:
                if(!ToolUtil.isEmpty(mJSON)){
                    Bundle bundle = new Bundle();
                    bundle.putString("data",mJSON);
                    bundle.putBoolean("isNewCar",mIsNewCar);
                    bundle.putBoolean("isLocalCity",mIsLocalCity);
                    HgzOr01C21Frm hgzOr01C21Frm = new HgzOr01C21Frm();
                    hgzOr01C21Frm.setArguments(bundle);
                    getSupportDelegate().start(hgzOr01C21Frm);
                }
                break;
            case R.id.ll_ggxx:
                if(!ToolUtil.isEmpty(mJSON)){
                    if(mGcjk.equals("A")){
                        String clxh = JSON.parseObject(mJSON).getString("clxh");
                        String fzrq = JSON.parseObject(mJSON).getString("ccrq"); //发证日期
                        Bundle bundle = new Bundle();
                        bundle.putString("clxh",clxh);
                        bundle.putString("fzrq",fzrq);
                        GgxxSelectFrm ggxxSelectFrm = new GgxxSelectFrm();
                        ggxxSelectFrm.setArguments(bundle);
                        getSupportDelegate().start(ggxxSelectFrm);
                    }else {
                        ToastUtils.showLong("进口车没有公告参数");
                    }

                }
                break;
            case R.id.tv_next_cyxm:
                if(checkForm()){
                    VehCheckInfo vehCheckInfo = getVehCheck(mIsNewCar);
                    if(checkData(vehCheckInfo)){
                        saveVehCheckIntoDB(vehCheckInfo);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",vehCheckInfo);
                        TakePhotoFrm takePhotoFrm = new TakePhotoFrm();
                        takePhotoFrm.setArguments(bundle);
                        getSupportDelegate().start(takePhotoFrm);
                    }
                }
                break;
        }
    }

    private void setBasicInitValueByVehCheckInfo(VehCheckInfo vehCheckInfo){
        mHphmFull = vehCheckInfo.getHphm();
        mIsSchoolCar = vehCheckInfo.getVeh_sfxc().equals("Y") ? true:false;
        mIsNewCar = vehCheckInfo.getYwlx().equals(CyConstant.YWLX_NEWCAR) ? true : false;
        mJSON = JSON.toJSONString(vehCheckInfo);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Bundle bundle = getArguments();
        mContext = getContext();
        mVehCheckInfo = (VehCheckInfo) bundle.getSerializable("vehCheckInfo");
        if(mVehCheckInfo==null){
            mJSON = bundle.getString("data");
            mIsSchoolCar = bundle.getBoolean("isSchoolCar");
            mIsNewCar = bundle.getBoolean("isNewCar");
            mIsLocalCity =  bundle.getBoolean("isLocalCity");
            mHphmFull = bundle.getString("hphmfull");
            String gcjk = bundle.getString("gcjk");
            mGcjk = ToolUtil.isEmpty(gcjk)?"A":gcjk;
            if(!mIsNewCar){
                mTextViewDjxx.setText("综合平台基本信息");
            }
        }else {
            setBasicInitValueByVehCheckInfo(mVehCheckInfo);
        }
        //针对在用车代码转换
        String conJson = convertJSON(mJSON,mIsNewCar);

        final ArrayList<MultipleItemEntity> data = new VehBasicInfoDataConverter()
                .setIsSchoolVeh(mIsSchoolCar)
                .setHphmFull(mHphmFull)
                .setContext(getContext())
                .setJsonData(conJson).convert();
        mAdapter = new VehBasicInfoAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new VehBasicInfoClickListener(this,getContext()));

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_SELECT_GGBH, new IGlobalCallback<Bundle>() {
                    @Override
                    public void executeCallback(@Nullable Bundle args) {
                        if(args !=null){
                            mGgbh = args.getString("BH");
                            mSfxny = args.getString("SFXNY");
                            mXnyzl = args.getString("XNYZL");
                        }
                    }
                });
    }

    private String convertJSON(String jsonObj,boolean isNewCar) {
        if(isNewCar || ToolUtil.isEmpty(jsonObj)){
            return jsonObj;
        }
        JSONObject jo = JSON.parseObject(jsonObj);
        String hpzl = ToolUtil.getValueByKeyInArrayRes(mContext,
                jo.getString("hpzl"),
                R.array.hpzl, R.array.hpzlcode);
        String syxz = ToolUtil.getValueByKeyInArrayRes(mContext,
                jo.getString("syxz"),
                R.array.syxz, R.array.syxzcode);
        String cllx = jo.getString("cllx");
        String[] arr = ToolUtil.getArrayByResouce(mContext, R.array.cllx);
        String cllxcode = null;
        for (String str : arr) {
            if (str.contains(cllx)) {
                cllxcode = str.split(":")[0];
                break;
            }
        }
        jo.remove("hpzl");
        jo.put("hpzl", hpzl);
        jo.remove("syxz");
        jo.put("syxz", syxz);
        jo.remove("cllx");
        jo.put("cllx", cllxcode);
        return jo.toString();
    }

    private boolean checkData(VehCheckInfo vehCheckInfo) {
        String ywlx = vehCheckInfo.getYwlx();
        String hdzk = vehCheckInfo.getHdzk();
        String gcjk = mGcjk;
        String bh = mGgbh;
        if(ywlx.equals("A")&&gcjk.equals("A")&&ToolUtil.isEmpty(bh)){
            ToastUtils.showLong("注册登记国产车需要先查看选择公告");
            return false;
        }
        if(hdzk!=null){
            if(!ToolUtil.isInteger(hdzk)){
                ToastUtils.showLong("核定载客为整数");
                return false;
            }
        }
        return true;
    }

    private boolean checkForm(){
        List<MultipleItemEntity> list  = mAdapter.getData();
        for (MultipleItemEntity entity: list) {
            String field = entity.getField(VehBasicItemFields.ITEM_LEFT_FIELD);
            String item_right_value_code = entity.getField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE);
            if(field.equals("ywlx")&&!mIsNewCar&&item_right_value_code.equals("A")){
                ToastUtils.showLong("在用车不能选择注册登记");
                return false;
            }
            if(!isItemCheckPass(entity)){
                return false;
            }
        }
        return true;
    }

    private VehCheckInfo getVehCheck(boolean isNewCar) {
        VehCheckInfo vehCheckInfo;
        if(mVehCheckInfo!=null){
            vehCheckInfo = mVehCheckInfo;
        }else {
            vehCheckInfo = new VehCheckInfo();
            final JSONObject jo = JSON.parseObject(mJSON);
            if(jo!=null&& !jo.isEmpty()){
                if(isNewCar) {
                    vehCheckInfo.setLsh(jo.getString("lsh"));
                }else {
                    vehCheckInfo.setLsh(DeviceUtil.createInUseCarLsh(getContext()));
                }
            }else {
                vehCheckInfo.setLsh(DeviceUtil.createInUseCarLsh(getContext()));
            }
            vehCheckInfo.setCycs(1);
            if (mIsSchoolCar) {
                vehCheckInfo.setVeh_sfxc("Y");
            } else {
                vehCheckInfo.setVeh_sfxc("N");
            }
            vehCheckInfo.setBh(mGgbh);
            vehCheckInfo.setSfxny(mSfxny);
            vehCheckInfo.setXnyzl(mXnyzl);
        }

        Map<String,String> items = new HashMap<>();
        List<MultipleItemEntity> list  = mAdapter.getData();
        for (MultipleItemEntity entity: list) {
            String item_left_field = entity.getField(VehBasicItemFields.ITEM_LEFT_FIELD);
            String itemValueCode = entity.getField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE);
            if(!item_left_field.equals("xczl") && !item_left_field.equals("qpzk_hpzk")){
                items.put(item_left_field,itemValueCode);
            }else if(item_left_field.equals("qpzk_hpzk")){
                if(!TextUtils.isEmpty(itemValueCode)){
                    String[] zks = itemValueCode.split("\\+");
                    if(zks[0]!=null){
                        items.put("qpzk",zks[0]);
                    }
                    if(zks[1]!=null){
                        items.put("hpzk",zks[1]);
                    }
                }
            }
        }
        BeanRefUtil.setFieldValue(vehCheckInfo,items);
        //出现hphm="NULL"情况
        String hphm = vehCheckInfo.getHphm();
        if(ToolUtil.isEmpty(hphm)){
            vehCheckInfo.setHphm(null);
        }
        vehCheckInfo.setCreatetime(new Date());
        return vehCheckInfo;
    }

    /**
     * 判断每个ITEM项是否为空
     * @param entity
     * @return false代表为空
     */
    private boolean isItemCheckPass(MultipleItemEntity entity){
        String itemName = entity.getField(VehBasicItemFields.ITEM_LEFT_TEXT);
        String itemValueCode = entity.getField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE);

        if(itemName.equals("业务类型")||itemName.equals("号牌种类")||
                itemName.equals("使用性质")){
            if(ToolUtil.isEmpty(itemValueCode)){
                ToastUtils.showLong("请选择"+itemName);
                return false;
            }
        }
        if(itemName.equals("车身颜色")){
            if(ToolUtil.isEmpty(itemValueCode)||!ToolUtil.isContainsStr(itemValueCode)){
                ToastUtils.showLong("请选择"+itemName);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == RequestCodes.FRM_CSYS){
                if (data != null) {
                    setCsysAndUpdateView(data);
                }
            }
        }
    }

    private void setCsysAndUpdateView(Intent data){
        String csys = data.getStringExtra("csys");
        String csysCode = data.getStringExtra("csysCode");
        if(ToolUtil.isEmpty(csys)||ToolUtil.isEmpty(csysCode)){
            return;
        }
        List<MultipleItemEntity> list  = mAdapter.getData();
        for (int i=0;i<list.size();i++) {
            MultipleItemEntity entity = list.get(i);
            String itemName = list.get(i).getField(VehBasicItemFields.ITEM_LEFT_TEXT);
            if(itemName.equals("车身颜色")){
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE,csys);
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE,csysCode);
                mAdapter.setData(i,entity);
                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            }
        }
    }

    private void saveVehCheckIntoDB(VehCheckInfo vehCheckInfo){
        new FastCyDbUtil().insertVehCheckInfo(vehCheckInfo);
    }




    @Override
    public void post(Runnable runnable) {}
}
