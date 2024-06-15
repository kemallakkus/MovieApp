plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id ("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
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

    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.material)
    implementation (libs.androidx.constraintlayout)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)

    // Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    // Navigation
    implementation (libs.navigation.fragment.ktx)
    implementation (libs.navigation.ui.ktx)

    // Glide
    implementation (libs.landscapist.glide.v219)

    // ViewModel
    implementation (libs.lifecycle.viewmodel.ktx)
    implementation (libs.activity.ktx)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit.gson)

    // Coroutines
    implementation (libs.coroutines.android)

    // Preferences DataStore
    implementation (libs.androidx.datastore.preferences)

    // Leak Canary
    debugImplementation (libs.leakcanary.android)

    // Lottie
    implementation(libs.lottie)
}