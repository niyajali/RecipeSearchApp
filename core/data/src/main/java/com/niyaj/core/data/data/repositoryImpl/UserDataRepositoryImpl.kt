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

package com.niyaj.core.data.data.repositoryImpl

import com.niyaj.core.data.repository.UserDataRepository
import com.niyaj.core.datastore.UserPreferencesDataSource
import com.niyaj.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataStore: UserPreferencesDataSource,
) : UserDataRepository {
    override val userData: Flow<UserData> = dataStore.userData

    override val getFavouritesRecipe: Flow<List<String>>
        get() = dataStore.userData.map { it.favouriteRecipes.toList() }

    override suspend fun setRecipeAddedToFavourite(recipeId: String, added: Boolean) =
        dataStore.setRecipeToFavourite(recipeId, added)

    override suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) =
        dataStore.setShouldHideOnboarding(shouldHideOnboarding)

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) =
        dataStore.setDynamicColorPreference(useDynamicColor)
}