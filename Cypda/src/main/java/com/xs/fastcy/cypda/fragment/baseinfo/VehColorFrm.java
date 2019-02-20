package com.xs.fastcy.cypda.fragment.baseinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.RequestCodes;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VehColorFrm extends LatteDelegate {

    @BindView(R2.id.sp_csys1)
    public AppCompatSpinner sp_csys1=null;

    @BindView(R2.id.sp_csys2)
    public AppCompatSpinner sp_csys2=null;

    @BindView(R2.id.sp_csys3)
    public AppCompatSpinner sp_csys3=null;

    private ArrayAdapter<String> mAdapter;
    private Context mContext;

    private String csys="";
    private String csysCode="";

    private String csys1="";
    private String csys1Code="";
    private String csys2="";
    private String csys2Code="";
    private String csys3="";
    private String csys3Code="";

    @Override
    public Object setLayout() {
        return R.layout.frm_veh_csys;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        initView();
    }

    @OnClick({R2.id.saveCsys,R2.id.tv_back})
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.tv_back:
                getSupportDelegate().pop();
                break;
            case R.id.saveCsys:
                /*csys = getNormalCsys(csys1)+getNormalCsys(csys2)+getNormalCsys(csys3);
                if(ToolUtil.isEmpty(csys)){
                    return;
                }
                csys = csys.substring(0, csys.length()-1);
                csysCode = getNormalCsysCode(csys1Code)+getNormalCsysCode(csys2Code)+getNormalCsysCode(csys3Code);
                csysCode = csysCode.substring(0,csysCode.length()-1);
                if(!csys.equals("")&&csys!=null){
                    setResult(csys,csysCode);
                }*/
                String[] colors = ToolUtil.getArrayByResouce(mContext,R.array.csys);
                List<String> list1 = Arrays.asList(colors);
                List<String> list2 = Arrays.asList(colors);
                List<String> list3 = Arrays.asList(colors);
                final ArrayList<String> arrayList1 = new ArrayList<String>(list1);
                final ArrayList<String> arrayList2 = new ArrayList<String>(list2);
                final ArrayList<String> arrayList3 = new ArrayList<String>(list3);
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            String tt = arrayList1.get(options1)+arrayList2.get(options2)+arrayList3.get(options3);
                        ToastUtils.showShort(tt);
                    }
                }).setSubmitText("确定").setCancelText("取消").build();
                pvOptions.setNPicker(arrayList1,arrayList2,arrayList3);
                pvOptions.show();
                break;
        }
    }

    private void setResult(String csys,String csysCode){
        if (getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra("csys", csys);
            intent.putExtra("csysCode", csysCode);
            getTargetFragment().onActivityResult(RequestCodes.FRM_CSYS, RESULT_OK, intent);
            getFragmentManager().popBackStack();
        }
    }

    void initView(){
        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.csys)));
        String[] csysArray = getResources().getStringArray(R.array.csys);
        mAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,csysArray);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_csys1.setAdapter(mAdapter);
        sp_csys2.setAdapter(mAdapter);
        sp_csys3.setAdapter(mAdapter);

        csys1Code = sp_csys1.getSelectedItem().toString();
        csys2Code = sp_csys2.getSelectedItem().toString();
        csys3Code = sp_csys3.getSelectedItem().toString();
        csys1 = ToolUtil.getValueByCodeInSpecialArray(mContext,csys1Code,R.array.csys);
        csys2 = ToolUtil.getValueByCodeInSpecialArray(mContext,csys2Code,R.array.csys);
        csys3 = ToolUtil.getValueByCodeInSpecialArray(mContext,csys3Code,R.array.csys);

    }

    private String getNormalCsys(String color){
        if(!color.equals("请选择颜色")){
            return color+",";
        }
        return "";
    }

    private String getNormalCsysCode(String colorCode){
        if(!colorCode.equalsIgnoreCase("NULL")){
            return colorCode+",";
        }
        return "";
    }

    @Override
    public void post(Runnable runnable) {}
}
