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

import kotlinx.coroutines.flow.Flow

class UserPreferenceRepositoryImpl(
    private val dataSource: UserPreferencesDataSource,
) : UserPreferenceRepository {
    override val userPreferences: Flow<UserPreferences>
        get() = dataSource.userPreferences

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        dataSource.setDynamicColorPreference(useDynamicColor)
    }

    override suspend fun setRecipeToFavourite(recipeId: String, bookmarked: Boolean) {
        dataSource.setRecipeToFavourite(recipeId, bookmarked)
    }

    override suspend fun setShouldHideOnboarding(showOnboarding: Boolean) {
        dataSource.setShouldHideOnboarding(showOnboarding)
    }

    override suspend fun clearInfo() {
        dataSource.clearInfo()
    }
}