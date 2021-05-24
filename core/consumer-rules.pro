#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

#Ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*


#CymChad:BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

#rxhttp
 #-keep class rxhttp.**{*;}

 #Rxjava RxAndroid
 -dontwarn rx.*
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQuene*Field*{
 long producerIndex;
 long consumerIndex;
 }

 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 #bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }
-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    <init>(...);
    void onBaseContextAttached(android.content.Context);
}
-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    <init>(...);
}
-keep public class * extends android.app.Application {
     <init>();
     void attachBaseContext(android.content.Context);
}
-keep class com.tencent.tinker.loader.TinkerTestAndroidNClassLoader {
    <init>(...);
}
-keep class com.iflytek.elpmobile.smartlearning.ThisApplication {
    <init>(...);
}
-keep class com.tencent.tinker.loader.** {
         <init>(...);
}


#Gson
-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}
-keep interface com.google.gson.**{*;}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#LitePal
-keep class org.litepal.** { *;}
-keep class * extends org.litepal.crud.LitePalSupport{ *;}

#保留一个完整的包
-keep class com.theone.mvvm.core.** { *; }