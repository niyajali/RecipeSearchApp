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

package com.niyaj.feature.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyaj.core.data.repository.RecipeRepository
import com.niyaj.core.data.repository.UserDataRepository
import com.niyaj.core.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val userDataRepository: UserDataRepository,
) : ViewModel() {


    val favouriteList = userDataRepository.getFavouritesRecipe.flatMapLatest { list ->
        repository.getFavouriteRecipes(list) {
            FavouriteUiState.Error(it.toString())
        }
    }.mapLatest {
        if (it.isEmpty()) FavouriteUiState.Empty else FavouriteUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavouriteUiState.Loading,
    )
}

sealed interface FavouriteUiState {
    data object Loading : FavouriteUiState

    data object Empty : FavouriteUiState

    data class Error(val message: String) : FavouriteUiState

    data class Success(val data: List<Recipe>) : FavouriteUiState
}