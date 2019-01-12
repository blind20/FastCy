package com.xs.fastcy.cypda.fragment.ggxx.ggimage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.app.Latte;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.file.FileUtil;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.fragment.takephoto.TakePhotoItemFields;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GgImageClickListener extends SimpleClickListener {

    private final GgImageFrm FRAGMENT;
    private final Context mContext;
    private String mImagePath;

    public GgImageClickListener(GgImageFrm fragment) {
        this.FRAGMENT = fragment;
        mContext = FRAGMENT.getContext();
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        final int id = entity.getField(MultipleFields.ID);
        final String thumbUrl = entity.getField(MultipleFields.IMAGE_URL);
        download(thumbUrl);
    }

    // 保存图片到手机
    public void download(final String url) {
        new AsyncTask<Void, Integer, File>() {
            @Override
            protected File doInBackground(Void... params) {
                File file = null;
                try {
                    FutureTarget<File> future = Glide
                            .with(mContext)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    file = future.get();
                    final File destFile = new File(FileUtil.CAMERA_PHOTO_DIR, "cy_ggzp.jpg");
                    String destFilePath = destFile.getPath();
                    FileUtil.copyFile(file,destFilePath);
                    mImagePath = destFilePath;
                    FileUtil.galleryAddPhoto(mContext,Uri.fromFile(new File(destFile.getPath())));
                } catch (Exception e) {
                    LatteLogger.e("download","download fail:"+e.getMessage());
                }
                return file;
            }

            @Override
            protected void onPostExecute(File file) {
                LatteLogger.e("onPostExecute","path="+mImagePath);
                if(mImagePath!=null){
                    ToastUtils.showLong("图片保存成功");
                    imageViewer(new File(mImagePath));
                }else {
                    ToastUtils.showLong("公告图片保存SD卡失败,是否没有打开APP存储权限?");
                }
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }



    /**
     * 查看已拍摄的照片
     * @param
     */
    private void imageViewer(File file) {
        Uri realUri = FileProvider.getUriForFile(mContext, "com.xs.fastcy.cypda.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(realUri,"image/*");
        mContext.startActivity(intent);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {}



}
