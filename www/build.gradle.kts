import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("multiplatform") version kotlinVersion
    val kvisionVersion: String by System.getProperties()
    id("io.kvision") version kvisionVersion
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

project.extensions.extraProperties["includeIosTargets"] = false
println("www: includeIosTargets: ${project.findProperty("includeIosTargets")}")

version = "1.0.0-SNAPSHOT"
group = "com.rascheduler.www"

repositories {
    google()
    mavenCentral()
    mavenLocal()
}

// Versions
val kotlinVersion: String by System.getProperties()
val kvisionVersion: String by System.getProperties()
val ktorVersion: String by project
val koinAnnotationsVersion: String by project
val logbackVersion: String by project

val mainClassName = "io.ktor.server.netty.EngineMain"

kotlin {
    jvmToolchain(17)
    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass.set(mainClassName)
        }
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "main.bundle.js"
                sourceMaps = false
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.kvision:kvision-server-ktor-koin:$kvisionVersion")
                implementation(project(":shared"))

                implementation(libs.kotlinx.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(compose.runtime)
                implementation(kotlin("reflect"))
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-auth:$ktorVersion")
                implementation("io.ktor:ktor-server-compression:$ktorVersion")
                implementation("io.insert-koin:koin-annotations:$koinAnnotationsVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(compose.runtime)
                implementation("io.kvision:kvision:$kvisionVersion")
                implementation("io.kvision:kvision-datetime:$kvisionVersion")
                implementation("io.kvision:kvision-toastify:$kvisionVersion")
                implementation("io.kvision:kvision-fontawesome:$kvisionVersion")
                implementation("io.kvision:kvision-bootstrap-icons:$kvisionVersion")
                implementation("io.kvision:kvision-maps:$kvisionVersion")
                implementation("io.kvision:kvision-routing-navigo-ng:$kvisionVersion")
                implementation("io.kvision:kvision-state:$kvisionVersion")
                implementation("io.kvision:kvision-tabulator:$kvisionVersion")
                implementation("io.kvision:kvision-state-flow:$kvisionVersion")
                implementation("io.kvision:kvision-redux-kotlin:$kvisionVersion")
                implementation("io.kvision:kvision-material:$kvisionVersion")
//                implementation("io.kvision:kvision-imask:$kvisionVersion")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation("io.kvision:kvision-testutils:$kvisionVersion")
            }
        }
    }
}

dependencies {
    add("kspJvm", "io.insert-koin:koin-ksp-compiler:$koinAnnotationsVersion")
}

afterEvaluate {
    tasks {
        getByName("kspKotlinJvm").apply {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}
