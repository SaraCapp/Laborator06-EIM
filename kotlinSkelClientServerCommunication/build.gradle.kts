plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "ro.pub.cs.systems.eim.lab06.clientservercommunication"
    compileSdk = 36

    defaultConfig {
        applicationId = "ro.pub.cs.systems.eim.lab06.clientservercommunication"
        minSdk = 26
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
    implementation(libs.androidx.fragment.ktx)
}
