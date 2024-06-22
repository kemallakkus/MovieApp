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

# Keep Android classes and interfaces
-keep class android.** { *; }
-keep interface android.** { *; }

# Keep Kotlin classes and interfaces
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Keep Retrofit annotations
-keepattributes Signature
-keepattributes Exceptions

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

##---------------End: proguard configuration for Gson  ----------

# Glide
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public class * extends com.bumptech.glide.GeneratedAppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# ViewModel and LiveData
-keep class androidx.lifecycle.** { *; }
-keep interface androidx.lifecycle.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keepclassmembers class * {
    @dagger.hilt.* <fields>;
    @dagger.hilt.* <methods>;
}
-keep class * {
    @dagger.hilt.* <init>(...);
}

# Kotlinx Serialization
-keepclassmembers class kotlinx.serialization.** {
    static final <fields>;
    public <methods>;
}

# Paging 3
-keep class androidx.paging.** { *; }
-keep interface androidx.paging.** { *; }

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Coroutines
-keep class kotlinx.coroutines.** { *; }

# Lottie
-keep class com.airbnb.lottie.** { *; }

# Your application package
-keep class com.example.movieapp.** { *; }
-keep interface com.example.movieapp.** { *; }
-keepclassmembers class com.example.movieapp.** {
    *;
}

# Allow obfuscation of Parcelable classes (if using Parcelize)
-keep class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Allow obfuscation of Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Prevent obfuscation of Android support libraries
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-dontwarn androidx.**

# Prevent obfuscation of support libraries
-keep class androidx.appcompat.** { *; }
-keep interface androidx.appcompat.** { *; }
-dontwarn androidx.appcompat.**

# Prevent obfuscation of material design libraries
-keep class com.google.android.material.** { *; }
-keep interface com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# Prevent obfuscation of constraint layout libraries
-keep class androidx.constraintlayout.** { *; }
-keep interface androidx.constraintlayout.** { *; }
-dontwarn androidx.constraintlayout.**

# Prevent obfuscation of kotlinx.coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}

