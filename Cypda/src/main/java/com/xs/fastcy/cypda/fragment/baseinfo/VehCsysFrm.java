package com.xs.fastcy.cypda.fragment.baseinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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

public class VehCsysFrm extends LatteDelegate {

    @BindView(R2.id.sp_csys1)
    public AppCompatSpinner sp_csys1=null;

    @BindView(R2.id.sp_csys2)
    public AppCompatSpinner sp_csys2=null;

    @BindView(R2.id.sp_csys3)
    public AppCompatSpinner sp_csys3=null;

    ArrayAdapter<String> mAdapter;

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
        initView();
    }

    @OnClick({R2.id.saveCsys,R2.id.tv_back})
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.tv_back:
                getSupportDelegate().pop();
                break;
            case R.id.saveCsys:
                csys = getNormalCsys(csys1)+getNormalCsys(csys2)+getNormalCsys(csys3);
                if(ToolUtil.isEmpty(csys)){
                    return;
                }
                csys = csys.substring(0, csys.length()-1);
                csysCode = getNormalCsysCode(csys1Code)+getNormalCsysCode(csys2Code)+getNormalCsysCode(csys3Code);
                csysCode = csysCode.substring(0,csysCode.length()-1);
                if(!csys.equals("")&&csys!=null){
                    setResult(csys,csysCode);
                }
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

        sp_csys1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                csys1 = getResources().getStringArray(R.array.csys)[pos];
                csys1Code = getResources().getStringArray(R.array.csyscode)[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        sp_csys2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                csys2 = getResources().getStringArray(R.array.csys)[pos];
                csys2Code = getResources().getStringArray(R.array.csyscode)[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        sp_csys3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                csys3 = getResources().getStringArray(R.array.csys)[pos];
                csys3Code = getResources().getStringArray(R.array.csyscode)[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
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
