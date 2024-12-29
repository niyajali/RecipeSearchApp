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

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
class UserPreferencesDataSource(
    private val settings: Settings,
    private val dispatcher: CoroutineDispatcher,
) {
    private val _userPreferences = MutableStateFlow(
        settings.decodeValue(
            key = KEY,
            serializer = UserPreferences.serializer(),
            defaultValue = settings.decodeValueOrNull(
                key = KEY,
                serializer = UserPreferences.serializer(),
            ) ?: UserPreferences.DEFAULT,
        ),
    )

    val userPreferences = _userPreferences.asStateFlow()

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        withContext(dispatcher) {
            val updatedUserPreferences =
                userPreferences.value.copy(useDynamicColor = useDynamicColor)
            _userPreferences.value = updatedUserPreferences
            settings.encodeValue(
                key = KEY,
                value = updatedUserPreferences,
                serializer = UserPreferences.serializer(),
            )
        }
    }

    suspend fun setRecipeToFavourite(recipeId: String, bookmarked: Boolean) {
        withContext(dispatcher) {
            val updatedUserPreferences = _userPreferences.value.copy(
                bookmarkedRecipeIds = if (bookmarked) {
                    _userPreferences.value.bookmarkedRecipeIds + recipeId
                } else {
                    _userPreferences.value.bookmarkedRecipeIds - recipeId
                },
            )
            _userPreferences.value = updatedUserPreferences
            settings.encodeValue(
                key = KEY,
                value = updatedUserPreferences,
                serializer = UserPreferences.serializer(),
            )
        }
    }

    suspend fun setShouldHideOnboarding(showOnboarding: Boolean) {
        withContext(dispatcher) {
            val updatedUserPreferences = userPreferences.value.copy(
                shouldShowOnboarding = showOnboarding,
            )
            _userPreferences.value = updatedUserPreferences
            settings.encodeValue(
                key = KEY,
                value = updatedUserPreferences,
                serializer = UserPreferences.serializer(),
            )
        }
    }


    suspend fun clearInfo() {
        withContext(dispatcher) {
            settings.clear()
        }
    }

    companion object {
        const val KEY = "user_preferences"
    }
}