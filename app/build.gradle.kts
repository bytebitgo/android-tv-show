plugins {
    alias(libs.plugins.android.application)
    kotlin("android") version "1.9.22"
}

android {
    namespace = "com.cmcc.myapplication"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        targetSdk = 34
        versionCode = 4
        versionName = "1.2.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            if (System.getenv("CI") == "true") {
                enableV1Signing = false
                enableV2Signing = false
                enableV3Signing = false
            } else {
                storeFile = file("../android_release.keystore")
                storePassword = System.getenv("KEY_STORE_PASSWORD") ?: "android"
                keyAlias = "android-app-key"
                keyPassword = System.getenv("KEY_PASSWORD") ?: "android"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            if (System.getenv("CI") != "true") {
                signingConfig = signingConfigs.getByName("release")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "market"
    productFlavors {
        create("gold") {
            dimension = "market"
            applicationId = "com.cmcc.xauusd"
            resValue("string", "app_name", "黄金行情")
            buildConfigField("String", "WEB_URL", "\"https://mt5.gslb.vip/mt/xauusd.html\"")
        }
        create("nasdaq") {
            dimension = "market"
            applicationId = "com.cmcc.index"
            resValue("string", "app_name", "纳斯达克指数")
            buildConfigField("String", "WEB_URL", "\"https://mt5.gslb.vip/mt/index.html\"")
        }
        create("oil") {
            dimension = "market"
            applicationId = "com.cmcc.usoil"
            resValue("string", "app_name", "原油")
            buildConfigField("String", "WEB_URL", "\"https://mt5.gslb.vip/mt/usoil.html\"")
        }
        create("usdjpy") {
            dimension = "market"
            applicationId = "com.cmcc.usdjpy"
            resValue("string", "app_name", "美日行情")
            buildConfigField("String", "WEB_URL", "\"https://mt5.gslb.vip/mt/usdjpy.html\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.all {
            this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            outputFileName = "${variant.name}.apk"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.leanback)
    implementation(libs.glide)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
} 