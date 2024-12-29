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

plugins {
    alias(libs.plugins.niyaj.kmp.library)
    alias(libs.plugins.niyaj.kmp.koin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.niyaj.core.datastore"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.serialization)
            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.core)
            implementation(projects.core.model)
            implementation(projects.core.common)
        }

        commonTest.dependencies {
            implementation(libs.multiplatform.settings.test)
        }

        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
