/*
 *
 *  * Copyright 2024 Sk Niyaj Ali
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
include(":core:database")
include(":core:datastore")
include(":core:datastore-proto")

include(":core:testing")
include(":core:data-test")
include(":core:analytics")

include(":feature")
include(":feature:home")
include(":feature:account")
include(":feature:search")
include(":feature:recipe-details")
include(":feature:favourite")
