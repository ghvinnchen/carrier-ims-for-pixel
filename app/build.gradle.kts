import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val namespaceName = "io.github.vvb2060.ims"
val applicationIdName = "io.github.vvb2060.ims.mod"

fun gitOutput(vararg args: String): String? = runCatching {
    val process = ProcessBuilder(listOf("git") + args)
        .directory(rootDir)
        .redirectErrorStream(true)
        .start()
    val output = process.inputStream.bufferedReader().use { it.readText() }.trim()
    if (process.waitFor() == 0 && output.isNotBlank()) output else null
}.getOrNull()

// Keep local/source archives buildable even when the .git directory is absent.
val gitVersionCode: Int = gitOutput("rev-list", "HEAD", "--count")?.toIntOrNull() ?: 1
val gitVersionName: String = gitOutput("rev-parse", "--short=8", "HEAD") ?: "local"
val appVersionName: String = libs.versions.app.version.get()

val debugApplicationIdSuffix: String =
    providers.gradleProperty("turboims.debugApplicationIdSuffix")
        .orElse(providers.environmentVariable("TURBOIMS_DEBUG_APPLICATION_ID_SUFFIX"))
        .orElse("")
        .get()

fun buildConfigString(value: String): String {
    val escaped = value
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
    return "\"$escaped\""
}

fun configuredString(propertyName: String, environmentName: String, defaultValue: String = ""): String {
    return providers.gradleProperty(propertyName)
        .orElse(providers.environmentVariable(environmentName))
        .orElse(defaultValue)
        .get()
}

// Optional release/custom signing. Debug builds fall back to Android's standard debug key.
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.isFile) file.inputStream().use(::load)
}

fun signingValue(propertyName: String, environmentName: String): String? =
    localProperties.getProperty(propertyName)
        ?.trim()
        ?.takeIf(String::isNotEmpty)
        ?: System.getenv(environmentName)?.trim()?.takeIf(String::isNotEmpty)

val signingStorePath = signingValue("SIGN_KEY_STORE_FILE", "SIGN_KEY_STORE_FILE")
val signingStorePassword = signingValue("SIGN_KEY_STORE_PASSWORD", "SIGN_KEY_STORE_PASSWORD")
val signingKeyAlias = signingValue("SIGN_KEY_ALIAS", "SIGN_KEY_ALIAS")
val signingKeyPassword = signingValue("SIGN_KEY_PASSWORD", "SIGN_KEY_PASSWORD")
val signingStoreFile = signingStorePath?.let(rootProject::file)
val hasCompleteSigning = listOf(
    signingStorePath,
    signingStorePassword,
    signingKeyAlias,
    signingKeyPassword
).all { !it.isNullOrBlank() } && signingStoreFile?.isFile == true

if (signingStorePath != null && signingStoreFile?.isFile != true) {
    logger.warn("CarrierIMS signing key was configured but the file does not exist: $signingStorePath")
}
if (!hasCompleteSigning) {
    logger.lifecycle("CarrierIMS: custom signing not configured; debug builds use the standard debug key and release builds are unsigned.")
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
        optIn.add("androidx.compose.material3.ExperimentalMaterial3ExpressiveApi")
    }
}

android {
    namespace = namespaceName
    compileSdk {
        version = release(libs.versions.android.compileSdk.get().toInt())
    }
    defaultConfig {
        applicationId = applicationIdName
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = gitVersionCode
        versionName = appVersionName
        buildConfigField(
            "String",
            "AD_API_BASE_URL",
            buildConfigString(
                configuredString(
                    "turboims.adApiBaseUrl",
                    "TURBOIMS_AD_API_BASE_URL",
                    "https://leads.3jiezhiwai.com"
                )
            )
        )
        buildConfigField(
            "String",
            "DODOPAY_SUPPORT_URL_TEMPLATE",
            buildConfigString(configuredString("turboims.dodopaySupportUrlTemplate", "TURBOIMS_DODOPAY_SUPPORT_URL_TEMPLATE"))
        )
        buildConfigField(
            "String",
            "DODOPAY_SUPPORT_FEED_URL",
            buildConfigString(configuredString("turboims.dodopaySupportFeedUrl", "TURBOIMS_DODOPAY_SUPPORT_FEED_URL"))
        )
        buildConfigField(
            "String",
            "BUSINESS_INTENT_BASE_URL",
            buildConfigString(
                configuredString(
                    "turboims.businessIntentBaseUrl",
                    "TURBOIMS_BUSINESS_INTENT_BASE_URL",
                    "https://leads.3jiezhiwai.com"
                )
            )
        )
        buildConfigField(
            "String",
            "BUSINESS_CONTACT_TEXT",
            buildConfigString(
                configuredString(
                    "turboims.businessContactText",
                    "TURBOIMS_BUSINESS_CONTACT_TEXT",
                    "合作联系：GitHub Issue"
                )
            )
        )
        buildConfigField(
            "String",
            "BUSINESS_CONTACT_URL",
            buildConfigString(
                configuredString(
                    "turboims.businessContactUrl",
                    "TURBOIMS_BUSINESS_CONTACT_URL",
                    "https://github.com/ryfineZ/carrier-ims-for-pixel/issues/new"
                )
            )
        )
        ndk {
            abiFilters.add("arm64-v8a")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        if (hasCompleteSigning) {
            create("sign") {
                storeFile = signingStoreFile
                storePassword = signingStorePassword
                keyAlias = signingKeyAlias
                keyPassword = signingKeyPassword
            }
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            @Suppress("UnstableApiUsage")
            vcsInfo.include = false
            versionNameSuffix = ".d$gitVersionCode.$gitVersionName"
            if (debugApplicationIdSuffix.isNotBlank()) {
                applicationIdSuffix = debugApplicationIdSuffix
            }
            if (hasCompleteSigning) {
                signingConfig = signingConfigs.getByName("sign")
            }
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            @Suppress("UnstableApiUsage")
            vcsInfo.include = false
            proguardFiles("proguard-rules.pro")
            versionNameSuffix = ".r$gitVersionCode.$gitVersionName"
            if (hasCompleteSigning) {
                signingConfig = signingConfigs.getByName("sign")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    lint {
        checkReleaseBuilds = false
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
    @Suppress("UnstableApiUsage")
    androidResources {
        localeFilters.add("en")
        localeFilters.add("zh-rCN")
    }
}

dependencies {
    compileOnly(project(":stub"))
    implementation(libs.shizuku.provider)
    implementation(libs.shizuku.api)
    implementation(libs.hiddenapibypass)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation("androidx.compose.animation:animation")
    implementation(libs.androidx.compose.material3)
    implementation(libs.material.icons.core)
    implementation(libs.material.icons.extended)
    implementation(libs.androidx.splashscreen)
    implementation(libs.lifecycle.viewmodel.ktx)

    testImplementation(kotlin("test"))
    testImplementation(libs.org.json)
}
