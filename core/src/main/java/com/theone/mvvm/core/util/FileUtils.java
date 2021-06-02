package com.theone.mvvm.core.util;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.theone.common.util.FileUtils.deleteDir;
import static com.theone.common.util.FileUtils.formatFileSize;
import static com.theone.common.util.FileUtils.getFileSize;
import static com.theone.common.util.FileUtils.saveBitmap;


/**
 * @Description:主要功能:文件管理
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:30
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String saveBitmapNewChildDir(String dir, Bitmap bitmap, int quality) {
        String fileName = "picture_" + System.currentTimeMillis() + ".jpg";
        return saveBitmapNewChildDir(dir, fileName, bitmap, quality);
    }

    public static String saveBitmapNewChildDir(String childDir, String fileName, Bitmap bitmap, int quality) {
        String path = FileDirectoryUtil.getPicturePath() + File.separator + childDir;
        return saveBitmap(path,fileName, bitmap, quality);
    }

    public static String saveBitmapNewChildDir(String childDir, Bitmap bitmap) {
        String fileName = "picture_" + System.currentTimeMillis() + ".jpg";
        return saveBitmap(childDir,fileName, bitmap, 100);
    }

    public static String saveBitmapNewChildDir(String childDir, String fileName, Bitmap bitmap) {
        return saveBitmap(childDir,fileName, bitmap, 100);
    }


    /**
     * 获取主目录下的所有文件大小
     * @return
     */
    public static String getIndexFileSize() {
        return formatFileSize( getFileSize(new File(FileDirectoryUtil.getIndexPath())));
    }

    /**
     * 删除
     * @return
     */
    public static boolean deleteIndexFile(){
        return deleteDir(new File(FileDirectoryUtil.getIndexPath()));
    }

    /**
     * 删除缓存
     * @return
     */
    public static boolean deleteCacheFile(){
        return deleteDir(new File(FileDirectoryUtil.getCachePath()));
    }


}
