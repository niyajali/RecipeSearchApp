pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RecipeSearchApp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":lint")
include(":benchmarks")
include(":ui-test-hilt-manifest")

include(":core:ui")
include(":core:data")
include(":core:designsystem")
include(":core:model")
include(":core:domain")
include(":core:network")
include(":core:common")
include(":core:testing")
include(":core:database")
include(":core:data-test")
include(":core:analytics")

include(":feature")
