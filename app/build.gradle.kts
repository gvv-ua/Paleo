import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "ua.gvv.paleo"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    (kotlinOptions as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions).apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")

    // Android
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.3.0-alpha01")

    // AndroidX lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")

    // Koin
    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:4.7.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.7.1")

    // Chucker
    implementation("com.github.ChuckerTeam.Chucker:library:3.0.0-alpha2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.8.2")
    implementation("com.squareup.retrofit2:converter-moshi:2.6.0")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.8.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Coil
    implementation("io.coil-kt:coil:0.11.0")
    implementation("io.coil-kt:coil-svg:0.11.0")

    // Test
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
