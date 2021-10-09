# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#----------------------基本指令------------------------
-dontwarn **
#代码混淆压缩比，在0和7之间，默认为5，一般不需要改
-optimizationpasses 5
#混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames
#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
#指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
#不做预校验，preverify是proguard的4个步骤之一
#Android不需要preverify，去掉这一步可加快混淆速度
-dontpreverify
#有了verbose这句话，混淆后就会生成映射文件
#包含有类名->混淆后类名的映射关系
#然后使用printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt
#指定混淆时采用的算法，后面的参数是一个过滤器
#这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#保护代码中的Annotation不被混淆，这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*,InnerClasses
#避免混淆泛型，这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature
#抛出异常时保留代码行号，在异常分析中可以方便定位
-keepattributes SourceFile,LineNumberTable

#外部启动入口不混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends androidx.*

#序列化不混淆
-keep public class * implements java.io.Serializable{
    *;
}
-keep public class * implements android.os.Parcelable{
    *;
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

#aidl文件不混淆
-keep public abstract interface android.os.Ifm1388Service{*;}
-keep public class com.tencent.* { *; }
-keep public class com.autopai.car.**{*;}


#枚举类不混淆
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

#三方依赖代码库不混淆
-keep public class android.** { *; }
-keep public class java.** { *; }
-keep public class javax.** { *; }
-keep public class org.** { *; }
-keep public class com.wt.** { *; }
-keep public class com.incall.proxy.** { *; }
-keep public class com.tencent.wecarspeech.** { *; }
-keep public class com.tencent.controlcenter.callback_transaction.** { *; }
-keep public class com.tencent.tai.** { *; }
-keep public class com.alibaba.fastjson.** { *; }


#support.v4/v7包不混淆
-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }

#Gson不混淆
-keep public class sun.misc.Unsafe { *; }
-keep public class com.idea.fifaalarmclock.entity.**
-keep public class com.google.gson.** { *; }
-keep public class com.google.code.gson.** { *; }
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#okhttp不混淆
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}
#okio不混淆
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#incallServerSDK
-keep public class com.incall.**{ *; }
-keep public interface com.incall.**{ *; }
-keep class com.incall.**{ *; }
-keep interface com.incall.**{ *; }

#PALDEMO 适配tai3.0 opensdk-pal
#gson库不能混淆
-keep class com.google.gson.**{*;}
#保持pal sdk中的实体类不被混淆
-keep class com.tencent.tai.pal.power.PowerInfo{*;}
-keep class com.tencent.tai.pal.VersionInfo{*;}
-keep class com.tencent.tai.pal.PlatformSupportInfo{*;}
-keep class com.tencent.tai.pal.platform.adapter{*;}


-keep public class com.tencent.taes.**{ *; }
-keep public interface com.tencent.taes.**{ *; }
-keep class com.tencent.taes.**{ *; }
-keep interface com.tencent.taes.**{ *; }

-keep public class com.tencent.wecarspeech.**{ *; }
-keep public interface com.tencent.wecarspeech.**{ *; }
-keep class com.tencent.wecarspeech.**{ *; }
-keep interface com.tencent.wecarspeech.**{ *; }

-keep public class com.xiandai.audio.**{*;}
-keep public interface com.xiandai.audio.**{*;}
-keep class com.xiandai.audio.**{*;}
-keep interface com.xiandai.audio.**{*;}

-keep public class com.xiandai.audio.audioManager**{*;}
-keep public interface com.xiandai.audio.audioManager**{*;}
-keep class com.xiandai.audio.audioManager**{*;}
-keep interface com.xiandai.audio.audioManager**{*;}


-keep public class com.hkmc.**{*;}
-keep public interface com.hkmc.**{*;}
-keep class com.hkmc.**{*;}
-keep interface com.hkmc.**{*;}

-keep public class com.hkmc.mode.**{*;}
-keep public interface com.hkmc.mode.**{*;}
-keep class com.hkmc.mode.**{*;}
-keep interface com.hkmc.mode.**{*;}



-keep public class com.tencent.wecarspeech.**{ *; }
-keep public interface com.tencent.wecarspeech.**{ *; }
-keep class com.tencent.wecarspeech.**{ *; }
-keep interface com.tencent.wecarspeech.**{ *; }


-keep public class com.tencent.wecarspeech.**{ *; }
-keep public interface com.tencent.wecarspeech.**{ *; }
-keep class com.tencent.wecarspeech.**{ *; }
-keep interface com.tencent.wecarspeech.**{ *; }


-keep public class com.tencent.wecarspeech.**{ *; }
-keep public interface com.tencent.wecarspeech.**{ *; }
-keep class com.tencent.wecarspeech.**{ *; }
-keep interface com.tencent.wecarspeech.**{ *; }


-keep public class com.tencent.wecarspeech.**{ *; }
-keep public interface com.tencent.wecarspeech.**{ *; }
-keep class com.tencent.wecarspeech.**{ *; }
-keep interface com.tencent.wecarspeech.**{ *; }


-keep class com.tencent.wecarcontrol.core.**{ *; }
-keep interface com.tencent.wecarcontrol.core.**{ *; }
