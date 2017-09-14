package com.evoucher.accv.e_voucher.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.evoucher.accv.e_voucher.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public class ImageUtil {
    
    
    
    
    public static void loadImage(String url, ImageView imageView) {
        x.image().bind(imageView, url);
    }
    
    public static void loadRoundImage(String url, ImageView imageView) {
        ImageOptions.Builder ib = new ImageOptions.Builder();
        ib
                .setCircular(true) // 设置圆形
                .setFadeIn(true)  // 淡入效果
                .setFailureDrawableId(R.mipmap.ic_launcher)  // 设置加载失败
                .setLoadingDrawableId(R.mipmap.ic_launcher_round);
        x.image().bind(imageView, url, ib.build());


//                //通过ImageOptions.Builder().set方法设置图片的属性
//                ImageOptions imageOptions = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
//                //ImageOptions.Builder()的一些其他属性：
//                .setCircular(true) //设置图片显示为圆形
//                .setSquare(true) //设置图片显示为正方形
//                .setCrop(true).setSize(200, 200) //设置大小
//                .setAnimation(animation) //设置动画
//                .setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
//                .setFailureDrawableId( int failureDrawable) //以资源id设置加载失败的动画
//                .setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
//                .setLoadingDrawableId( int loadingDrawable) //以资源id设置加载中的动画
//                .setIgnoreGif(false) //忽略Gif图片
//                .setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
//                .setRaduis( int raduis) //设置拐角弧度
//                .setUseMemCache(true) //设置使用MemCache，默认true


//                // assets file
//                x.image().bind(imageView, "assets://test.gif", imageOptions);
//                // local file
//                x.image().bind(imageView, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
//                x.image().bind(imageView, "/sdcard/test.gif", imageOptions);
//                x.image().bind(imageView, "file:///sdcard/test.gif", imageOptions);
//                x.image().bind(imageView, "file:/sdcard/test.gif", imageOptions);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context
     * @param imageUri
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }
    
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
