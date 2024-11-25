// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.0")
            version("core-ktx", "1.12.0")
            version("appcompat", "1.6.1")
            version("material", "1.11.0")

            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            library("androidx-core-ktx", "androidx.core", "core-ktx").versionRef("core-ktx")
            library("androidx-appcompat", "androidx.appcompat", "appcompat").versionRef("appcompat")
            library("material", "com.google.android.material", "material").versionRef("material")
        }
    }
}