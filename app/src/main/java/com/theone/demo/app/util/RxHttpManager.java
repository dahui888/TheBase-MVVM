package com.theone.demo.app.util;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛


import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.theone.mvvm.base.BaseApplication;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cahce.CacheMode;
import rxhttp.wrapper.ssl.HttpsUtils;

/**
 * @author The one
 * @date 2020/8/18 0018
 * @describe RxHttp相关工具
 * @email 625805189@qq.com
 * @remark
 */
public class RxHttpManager {

    private static PersistentCookieJar cookieJar;
    private static File mCacheFile;

    public static PersistentCookieJar getCookieJar(){
        if(null == cookieJar){
            cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.app));
        }
        return cookieJar;
    }

    public static OkHttpClient getHttpClient(HttpBuilder builder) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        if (builder.isNeedCookie())
            httpBuilder.cookieJar(getCookieJar());
        httpBuilder.connectTimeout(builder.getOutTime(), TimeUnit.SECONDS)
                .readTimeout(builder.getReadTime(), TimeUnit.SECONDS)
                .writeTimeout(builder.getWriteTime(), TimeUnit.SECONDS)
                .sslSocketFactory(HttpsUtils.getSslSocketFactory().sSLSocketFactory, HttpsUtils.getSslSocketFactory().trustManager) //添加信任证书
                .hostnameVerifier((hostname, session) -> true); //忽略host验证;
        OkHttpClient client = httpBuilder.build();
        OkHttpUtils.initClient(client);
        initCacheMode(builder);
        return client;
    }

    private static void initCacheMode(HttpBuilder builder) {
        mCacheFile = new File(builder.getCacheFilePath(), builder.getCacheFileName());
        RxHttpPlugins.setCache(mCacheFile, builder.getCacheMaxSize(), builder.getCacheMode(), builder.getCacheValidTime());
    }

    public static void clearCache(){
        if(null != mCacheFile){
            mCacheFile.deleteOnExit();
        }
    }

    public static class HttpBuilder {

        private boolean isNeedCookie = false;
        private String cookieFileName = "RxHttpCookie";
        private String cookieFilePath = FileDirectoryUtil.getCachePath();

        private String cacheFileName = "RxHttCache";
        private String cacheFilePath = FileDirectoryUtil.getCachePath();
        private long cacheMaxSize = 1000 * 100;
        private CacheMode cacheMode = CacheMode.ONLY_NETWORK;
        private long cacheValidTime = -1;

        private int outTime = 10;
        private int readTime = 10;
        private int writeTime = 10;

        public boolean isNeedCookie() {
            return isNeedCookie;
        }

        public HttpBuilder setNeedCookie(boolean needCookie) {
            isNeedCookie = needCookie;
            return this;
        }

        public String getCookieFileName() {
            return cookieFileName;
        }

        public HttpBuilder setCookieFileName(String cookieFileName) {
            this.cookieFileName = cookieFileName;
            return this;
        }

        public String getCookieFilePath() {
            return cookieFilePath;
        }

        public HttpBuilder setCookieFilePath(String cookieFilePath) {
            this.cookieFilePath = cookieFilePath;
            return this;
        }

        public String getCacheFileName() {
            return cacheFileName;
        }

        public HttpBuilder setCacheFileName(String cacheFileName) {
            this.cacheFileName = cacheFileName;
            return this;
        }

        public String getCacheFilePath() {
            return cacheFilePath;
        }

        public HttpBuilder setCacheFilePath(String cacheFilePath) {
            this.cacheFilePath = cacheFilePath;
            return this;
        }

        public long getCacheMaxSize() {
            return cacheMaxSize;
        }

        public HttpBuilder setCacheMaxSize(long cacheMaxSize) {
            this.cacheMaxSize = cacheMaxSize;
            return this;
        }

        public CacheMode getCacheMode() {
            return cacheMode;
        }

        public HttpBuilder setCacheMode(CacheMode cacheMode) {
            this.cacheMode = cacheMode;
            return this;
        }

        public long getCacheValidTime() {
            return cacheValidTime;
        }

        public HttpBuilder setCacheValidTime(long cacheValidTime) {
            this.cacheValidTime = cacheValidTime;
            return this;
        }

        public int getOutTime() {
            return outTime;
        }

        public HttpBuilder setOutTime(int outTime) {
            this.outTime = outTime;
            return this;
        }

        public int getReadTime() {
            return readTime;
        }

        public HttpBuilder setReadTime(int readTime) {
            this.readTime = readTime;
            return this;
        }

        public int getWriteTime() {
            return writeTime;
        }

        public HttpBuilder setWriteTime(int writeTime) {
            this.writeTime = writeTime;
            return this;
        }
    }

}
