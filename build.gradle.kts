buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    id ("com.android.application") version "8.2.1" apply false
    id ("com.android.library") version "8.2.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.5.1" apply false
}