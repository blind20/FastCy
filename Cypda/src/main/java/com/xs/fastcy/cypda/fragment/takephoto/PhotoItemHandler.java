package com.xs.fastcy.cypda.fragment.takephoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.FileUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.flj.latte.util.file.FileUtil;
import com.flj.latte.util.file.ImageUtil;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhotoItemHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final LatteDelegate DELEGATE;
    private final Uri mUri;

    public PhotoItemHandler(LatteDelegate delegate,Uri uri) {
        this.DELEGATE = delegate;
        this.mUri = uri;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == com.diabin.latte.R.id.photodialog_btn_cancel) { //取消
            DIALOG.cancel();
        } else if (id == com.diabin.latte.R.id.photodialog_btn_take) {  //查看照片
            imageViewer(mUri);
            DIALOG.cancel();
        } else if (id == com.diabin.latte.R.id.photodialog_btn_native) { //删除照片
            final IGlobalCallback<String> deletePhotoCallback = CallbackManager
                    .getInstance()
                    .getCallback(CallbackType.ON_DELETE_PHOTO);
            if (deletePhotoCallback != null) {
                deletePhotoCallback.executeCallback("DELETE");
            }
            DIALOG.cancel();
        }
    }

    /**
     * 查看已拍摄的照片
     * @param uri
     */
    private void imageViewer(Uri uri) {
        Context context = DELEGATE.getContext();
        File realFile =
                FileUtils.getFileByPath(FileUtil.getRealFilePath(context, uri));
        Uri realUri = FileProvider.getUriForFile(context, "com.xs.fastcy.cypda.fileprovider", realFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(realUri,"image/*");
        context.startActivity(intent);
    }



}
