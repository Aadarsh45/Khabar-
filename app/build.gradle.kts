plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.khabbar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.khabbar"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
       versionName = "1.0"
//        val myApiKey: String = project.property("MY_API_KEY") as? String ?: ""
//        buildConfigField("String", "API_KEY", "\"${property("MY_API_KEY")}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
//        getByName("release") {
//            // You might want to do the same for release or other build types
//            // if not already covered by defaultConfig
//            val myApiKey: String = project.property("MY_API_KEY") as? String ?: ""
//            buildConfigField("String", "API_KEY", "\"$myApiKey\"")
//            // ...
//        }

        release {

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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

    //splash screen
    implementation (libs.androidx.core.splashscreen)

    //preference
    implementation (libs.androidx.datastore.preferences)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.kotlinx.coroutines.core)


    implementation (libs.logging.interceptor)

// Paging
    implementation (libs.androidx.paging.runtime.ktx)

// Room (optional for local caching)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.paging)
    ksp (libs.androidx.room.compiler)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //Compose Foundation
    implementation (libs.androidx.foundation)



    // This variable seems unused for paging-compose
    val paging_version = "3.1.1"
    implementation (libs.androidx.paging.runtime) // You have paging-runtime
    implementation (libs.androidx.paging.compose)


    implementation ("androidx.compose.material:material:1.6.1")  // or latest version

    // Compose Foundation (for basic components)
    implementation ("androidx.compose.foundation:foundation:1.6.1")



}