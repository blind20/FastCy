package com.xs.fastcy.cypda.fragment.baseinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.RequestCodes;
import com.xs.fastcy.cypda.util.ToolUtil;

public class VehBasicInfoClickListener extends SimpleClickListener {

    private final VehBasicInfoFrm FRAGMENT;
    private final Context mContext;
    private String[] mYwlxArray;
    private String[] mHpzlArray;
    private String[] mSyxzArray;
    private String[] mCllxArray;
    private String[] mYwlxCodeArray;
    private String[] mHpzlCodeArray;
    private String[] mSyxzCodeArray;

    public VehBasicInfoClickListener(VehBasicInfoFrm fragment,Context context){
        this.FRAGMENT = fragment;
        this.mContext = context;
        mYwlxArray = mContext.getResources().getStringArray(R.array.ywlx);
        mYwlxCodeArray = mContext.getResources().getStringArray(R.array.ywlxcode);
        mHpzlArray = mContext.getResources().getStringArray(R.array.hpzl);
        mHpzlCodeArray = mContext.getResources().getStringArray(R.array.hpzlcode);
        mSyxzArray = mContext.getResources().getStringArray(R.array.syxz);
        mSyxzCodeArray = mContext.getResources().getStringArray(R.array.syxzcode);
        mCllxArray = mContext.getResources().getStringArray(R.array.cllx);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        final int id = entity.getField(MultipleFields.ID);
        switch (id){
            case 0:
                getItemsDialog(ToolUtil.getArrayByResouce(mContext,R.array.xczl),
                        ToolUtil.getArrayByResouce(mContext,R.array.xczlCode),
                        view,R.id.tv_arrow_value,entity);
                break;
            case 1:
                getItemsDialog(mYwlxArray,mYwlxCodeArray,view,R.id.tv_arrow_value,entity);
                break;
            case 3:
                getItemsDialog(mHpzlArray,mHpzlCodeArray,view,R.id.tv_arrow_value,entity);
                break;
            case 5:
                getItemsDialog(mSyxzArray,mSyxzCodeArray,view,R.id.tv_arrow_value,entity);
                break;
            case 6:
                getItemsSpDialog(mCllxArray,view,R.id.tv_arrow_value,entity);
                break;
            case 7:
                VehCsysFrm vehCsysFrm = entity.getField(VehBasicItemFields.DELEGATE);
                vehCsysFrm.setTargetFragment(FRAGMENT,RequestCodes.FRM_CSYS);
                FRAGMENT.getSupportDelegate().start(vehCsysFrm);
//              FRAGMENT.getSupportDelegate().startForResult(vehCsysFrm,RequestCodes.FRM_CSYS);
                break;
            case 2://车辆识别代号
            case 8://核定人数
                editItemDialog(view,R.id.tv_arrow_value,entity);
                break;
            default:
                break;
        }
    }



    private void getItemsDialog(final String[] valueArray, final String[] codeArray,
                                final View itemView, final int ResId,
                                final MultipleItemEntity entity){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setSingleChoiceItems(valueArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final TextView textView = (TextView)itemView.findViewById(ResId);
                textView.setText(valueArray[which]);
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE,valueArray[which]);
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE,codeArray[which]);
                dialog.cancel();
            }
        }).show();
    }

    private void editItemDialog(final View itemView, final int ResId, final MultipleItemEntity entity){
        final EditText et = new EditText(mContext);
        String itemName = entity.getField(VehBasicItemFields.ITEM_LEFT_TEXT);
        String itemValueCode = entity.getField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE);
        String Title="请输入" + itemName;
        if(itemName.equals("车辆识别代号")){
            et.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }else if (itemName.equals("核定人数")){
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (itemValueCode != null) {
            et.setText(itemValueCode);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(Title).setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String value = et.getText().toString().trim();
                        entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE,value);
                        entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE,value);
                        final TextView textView = itemView.findViewById(ResId);
                        textView.setText(value);
                    }
                }).setNegativeButton("取消",null).show();
    }

    private void getItemsSpDialog(final String[] valueArray,final View itemView, final int ResId,final MultipleItemEntity entity){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setSingleChoiceItems(valueArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final TextView textView = itemView.findViewById(ResId);
                String item = valueArray[which];
                textView.setText(item);
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE,item);
                entity.setField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE,item.split(":")[0]);
                dialog.cancel();
            }
        }).show();
    }


    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {}
}
