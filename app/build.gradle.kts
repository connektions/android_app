plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.connektions"
    compileSdk = libs.versions.compileSdk.get().toIntOrNull()

    defaultConfig {
        applicationId = "com.example.connektions"
        minSdk = libs.versions.minSdk.get().toIntOrNull()
        targetSdk = libs.versions.targetSdk.get().toIntOrNull()
        versionCode = libs.versions.versionCode.get().toIntOrNull()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.compose)
    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.androidTests)
    debugImplementation(libs.bundles.debug)

}
