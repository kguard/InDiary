pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
rootProject.name = "InDiary"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:database")
include(":core:model")
include(":core:designsystem")
include(":core:network")
include(":core:ui")
