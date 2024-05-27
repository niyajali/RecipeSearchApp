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

package com.niyaj.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyaj.core.common.result.Result
import com.niyaj.core.data.repository.RecipeRepository
import com.niyaj.core.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val fetchingIndex = MutableStateFlow(10)

    val popularRecipes = fetchingIndex.flatMapLatest { limit ->
        repository.getAllRandomRecipes(
            limit = limit,
        )
    }.mapLatest {
        when (it) {
            is Result.Loading -> HomeUiState.Loading
            is Result.Error -> HomeUiState.Error(it.exception.message)
            is Result.Success -> HomeUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading,
    )

    val randomRecipeList = fetchingIndex.flatMapLatest { page ->
        repository.getAllRandomRecipes(limit = page)
    }.mapLatest {
        when (it) {
            is Result.Loading -> HomeUiState.Loading
            is Result.Error -> HomeUiState.Error(it.exception.message)
            is Result.Success -> HomeUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading,
    )
}


@Stable
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: String?) : HomeUiState
    data class Success(val data: List<Recipe>) : HomeUiState
}