package com.xs.fastcy.cypda.fragment.ggxx;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;


public class GgxxSelectClickListener extends SimpleClickListener {

    private final GgxxSelectFrm DELEGATE;

    public GgxxSelectClickListener(GgxxSelectFrm delegate){
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        JSONObject jsonObject = entity.getField(GgxxSelectItemFields.ITEM_GGXX_JSON);

        final IGlobalCallback<Bundle> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.TAG_SELECT_GGBH);
        if (callback != null) {
            Bundle bundle= new Bundle();
            bundle.putString("BH",jsonObject.getString("BH"));
            bundle.putString("SFXNY",jsonObject.getString("SFXNY"));
            bundle.putString("XNYZL",jsonObject.getString("XNYZL"));
            callback.executeCallback(bundle);
        }

        GgxxDetailFrm ggxxDetailFrm = new GgxxDetailFrm();
        Bundle bundle = new Bundle();
        bundle.putString("data",jsonObject.toString());
        ggxxDetailFrm.setArguments(bundle);
        DELEGATE.getSupportDelegate().start(ggxxDetailFrm);
    }




    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
