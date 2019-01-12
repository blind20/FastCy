package com.xs.fastcy.cypda.fragment.takephoto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.camera.CameraHandler;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.flj.latte.util.file.FileUtil;
import com.flj.latte.util.file.ImageUtil;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.entity.VehPhoto;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicItemFields;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TakePhotoClickListener extends SimpleClickListener implements ISuccess,IError,IFailure {

    private AppCompatTextView mHint;
    private final TakePhotoFrm FRAGMENT;
    private final VehCheckInfo mVehCheckInfo;
    private final Context mContext;
    private String mZpName;
    private String mZpzl;
    private String mImagePath;
    private Uri mTempReUploadUri;

    public TakePhotoClickListener(TakePhotoFrm fragment, VehCheckInfo vehCheckInfo, AppCompatTextView hint) {
        this.FRAGMENT = fragment;
        this.mVehCheckInfo = vehCheckInfo;
        this.mContext = FRAGMENT.getContext();
        this.mHint = hint;
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
        mHint.setText("");
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        final String zpCode = entity.getField(TakePhotoItemFields.PHOTO_ZPZL_CODE);
        final String zpName = entity.getField(TakePhotoItemFields.PHOTO_ZPZL_NAME);
        mZpName = zpName;
        mZpzl = zpCode;
        final Uri uri = entity.getField(TakePhotoItemFields.PHOTO_FILE_URI);
        final AppCompatImageView photo = view.findViewById(R.id.iv_add_photo);
        if(uri==null) {
            CallbackManager.getInstance().setParam(zpCode)
                    .addCallback(CallbackType.ON_TAKE_PHOTO, new IGlobalCallback<Uri>() {
                        @Override
                        public void executeCallback(Uri args) {
                            photo.setVisibility(View.VISIBLE);
                            if(args!=null) {
                                Glide.with(FRAGMENT).load(args).apply(OPTIONS).into(photo);
                                entity.setField(TakePhotoItemFields.PHOTO_FILE_URI, args);
                                adapter.setData(position, entity);
                                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
                                mImagePath = args.toString();
                                mTempReUploadUri = args;
                                uploadImag(args, zpCode, mVehCheckInfo);
                            }
                        }
                    });
            FRAGMENT.startCameraWithCheck();
        }else{
            CallbackManager.getInstance()
                    .addCallback(CallbackType.ON_DELETE_PHOTO, new IGlobalCallback<String>() {
                        @Override
                        public void executeCallback(@Nullable String args) {
                            if(args.equals("DELETE")){
                                int resourceId = R.mipmap.ic_photo_add;
                                Glide.with(FRAGMENT).load(resourceId).apply(OPTIONS).into(photo);
                                entity.setField(TakePhotoItemFields.PHOTO_FILE_URI, null);
                                adapter.setData(position, entity);
                                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
                            }
                        }
                    });
            new PhotoItemHandler(FRAGMENT,uri).beginCameraDialog();
        }
    }

    private void uploadImag(Uri uri, String zpCode,VehCheckInfo vehCheckInfo) {
        Map<String,Object> params = new HashMap<>();
        params.put("lsh",vehCheckInfo.getLsh());
        params.put("clsbdh",vehCheckInfo.getClsbdh());
        params.put("hpzl",vehCheckInfo.getHpzl());
        params.put("zpzl",zpCode);
        params.put("jccs",vehCheckInfo.getCycs());
        if (!ToolUtil.isEmpty(vehCheckInfo.getHphm())){
            params.put("hphm",vehCheckInfo.getHphm());
        }

        Bitmap bmp = null;
        try {
            bmp = ImageUtil.getBitmap(mContext,uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64 = ToolUtil.bitmaptoString(bmp);
        params.put("imageStr",base64);
        //Map转换成JSONObject上传
        JSONObject jo = JSONObject.parseObject(JSON.toJSONString(params));
        RestClient.builder().url("pdaService/uploadImageFile")
                .loader(mContext)
                .raw(jo.toString())
                .success(this).error(this).failure(this).build().post();
    }


    @Override
    public void onSuccess(String response) {
        if(!ToolUtil.isEmpty(response)){
            JSONObject jo = JSON.parseObject(response);
            int state = jo.getInteger("state");
            String msg = jo.getString("message");
            if (state==CyConstant.REQ_OK){
                VehPhoto vehPhoto = new VehPhoto();
                vehPhoto.setLsh(mVehCheckInfo.getLsh());
                vehPhoto.setClsbdh(mVehCheckInfo.getClsbdh());
                vehPhoto.setZpName(mZpName);
                vehPhoto.setZpzl(mZpzl);
                vehPhoto.setImgPath(mImagePath);
                vehPhoto.setHphm(mVehCheckInfo.getHphm());
                vehPhoto.setJccs(mVehCheckInfo.getCycs());
                new FastCyDbUtil(mContext).insertVehPhoto(vehPhoto);
                mHint.setText(mZpName+"上传成功");
            }else {
                mHint.setText(mZpName+"上传失败:"+msg);
            }
            if(!TextUtils.isEmpty(msg)){
                ToastUtils.showShort(msg);
            }
        }
    }


    @Override
    public void onError(int code, String msg) {
        mHint.setText(mZpName+"上传失败,服务端问题,重新上传:"+msg+",code="+code);
        ToastUtils.showShort(mZpName+"上传失败,服务端问题,重新上传");
    }

    @Override
    public void onFailure() {
        mHint.setText(mZpName+"上传失败,网络故障,重新上传");
        ToastUtils.showShort(mZpName+"上传失败,网络故障");
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        String[] array = new String[]{"重新上传"};
        builder.setTitle("请选择操作").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadImag(mTempReUploadUri,mZpzl,mVehCheckInfo);
                dialog.cancel();
            }
        }).show();
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {}


}
