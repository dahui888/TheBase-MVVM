package com.theone.mvvm.util.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import rxhttp.HttpSender;


// 注意这个注解一定要加上，HttpGlideModule是自定义的名字
@GlideModule
public final class OkHttpGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // 注意这里用我们刚才现有的Client实例传入即可
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(getOkHttpClient()));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    /**
     * 添加拦截器
     * @return
     */
    private  OkHttpClient getOkHttpClient() {
      return   HttpSender.newOkClientBuilder().addInterceptor(new GlideProgressInterceptor()).build();
//        X509TrustManager trustAllCert = new X509TrustManagerImpl();
//        SSLSocketFactory sslSocketFactory = new SSLSocketFactoryImpl(trustAllCert);
//        return new OkHttpClient.Builder()
//                .addInterceptor(new GlideProgressInterceptor())
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory, trustAllCert) //添加信任证书
//                .hostnameVerifier((hostname, session) -> true) //忽略host验证
//                .build();
    }
}
