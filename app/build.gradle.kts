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
        versionCode = 2
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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