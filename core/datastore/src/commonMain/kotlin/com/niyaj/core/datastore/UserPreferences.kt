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

package com.niyaj.core.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val bookmarkedRecipeIds: Set<String> = emptySet(),
    val hasDoneListToMapMigration: Boolean,
    val shouldShowOnboarding: Boolean,
    val useDynamicColor: Boolean,
) {
    companion object {
        val DEFAULT = UserPreferences(
            hasDoneListToMapMigration = false,
            shouldShowOnboarding = true,
            useDynamicColor = true,
        )
    }
}