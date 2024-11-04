plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.pascal.rentalio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pascal.rentalio"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    applicationVariants.all{
        outputs.all {
            if(name.contains("release"))
                (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl)
                    .outputFileName = "app-$name-$versionName.apk"
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

    flavorDimensions += "environment"
    var baseUrl : String

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            baseUrl = "api.jsonbin.io/v3"
            dimension = "environment"
            resValue("string", "app_name", "\"Rentalio Dev\"")
            buildConfigField("String", "BASE_URL", "\"" + baseUrl + "\"")
        }

        create("staging") {
            applicationIdSuffix = ".staging"
            baseUrl = "api.jsonbin.io/v3"
            dimension = "environment"
            resValue("string", "app_name", "\"Rentalio Staging\"")
            buildConfigField("String", "BASE_URL", "\"" + baseUrl + "\"")
        }

        create("prod") {
            applicationIdSuffix = ".prod"
            baseUrl = "api.jsonbin.io/v3"
            dimension = "environment"
            resValue("string", "app_name", "\"Rentalio\"")
            buildConfigField("String", "BASE_URL", "\"" + baseUrl + "\"")
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // UI
    implementation(libs.constraintlayout.compose)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.material.icons.core)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.pager)
    implementation(libs.feather.icons)

    // Koin
    implementation(libs.koin)

    // Paging
    implementation(libs.paging.common.ktx)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    // Navigation
    implementation(libs.navigation)

    // Retrofit
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.coroutines.core)
    api(libs.coroutines.android)
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    kapt(libs.roomCompiler)

    implementation("com.midtrans:uikit:2.0.0-SANDBOX")
    implementation("com.midtrans:uikit:1.23.1")
}