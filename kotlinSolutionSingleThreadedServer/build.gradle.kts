plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "ro.pub.cs.systems.eim.lab06.singlethreadedserver"
    compileSdk = 36

    defaultConfig {
        applicationId = "ro.pub.cs.systems.eim.lab06.singlethreadedserver"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
}
