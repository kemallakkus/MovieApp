import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.reader().use { load(it) }
    }
}

val apiKey = localProperties.getProperty("API_KEY") ?: System.getenv("API_KEY")

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.example.movieapp.HiltTestRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Glide
    implementation(libs.landscapist.glide.v219)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Coroutines
    implementation(libs.coroutines.android)

    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // Leak Canary
    debugImplementation(libs.leakcanary.android)

    // Lottie
    implementation(libs.lottie)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Paging 3
    implementation(libs.androidx.paging.runtime.ktx)

    // Chucker for debugging purposes
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)

    //Shimmer Effect
    implementation (libs.shimmer)

    testImplementation (libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.truth)
    testImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation (libs.robolectric)
    testImplementation (libs.mockito.core)

    implementation(libs.retrofit2.kotlinx.serialization.converter)
}