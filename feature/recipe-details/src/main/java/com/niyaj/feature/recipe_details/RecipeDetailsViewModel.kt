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

package com.niyaj.feature.recipe_details

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyaj.core.common.result.Result
import com.niyaj.core.data.repository.RecipeRepository
import com.niyaj.core.data.repository.UserDataRepository
import com.niyaj.core.model.Recipe
import com.niyaj.core.model.details.RecipeDetails
import com.niyaj.core.model.nutrition_details.NutritionDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val userDataRepository: UserDataRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val recipeId = savedStateHandle.get<String>("recipeId")?.toInt() ?: 0

    val isAddedAsFavourite = snapshotFlow { recipeId }.flatMapLatest { _ ->
        userDataRepository.getFavouritesRecipe
    }.mapLatest {
        it.contains(recipeId.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )

    val recipeDetails = snapshotFlow { recipeId }.flatMapLatest { recipeId ->
        repository.getRecipeDetails(recipeId, true)
    }.mapLatest {
        when (it) {
            is Result.Loading -> RecipeDetailsState.Loading
            is Result.Error -> RecipeDetailsState.Error(it.exception.message.toString())
            is Result.Success -> {
                RecipeDetailsState.Success(it.data)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecipeDetailsState.Loading,
    )

    val nutritionDetails = snapshotFlow { recipeId }.flatMapLatest { recipeId ->
        repository.getNutritionDetails(recipeId)
    }.mapLatest {
        when (it) {
            is Result.Loading -> NutritionDetailsState.Loading
            is Result.Error -> NutritionDetailsState.Error(it.exception.message.toString())
            is Result.Success -> NutritionDetailsState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NutritionDetailsState.Loading,
    )

    val similarRecipes = snapshotFlow { recipeId }.flatMapLatest { recipeId ->
        repository.getSimilarRecipes(recipeId, 10)
    }.mapLatest {
        when (it) {
            is Result.Loading -> SimilarRecipesState.Loading
            is Result.Error -> SimilarRecipesState.Error(it.exception.message.toString())
            is Result.Success -> {
                if (it.data.isEmpty()) {
                    SimilarRecipesState.Empty
                } else {
                    SimilarRecipesState.Success(it.data)
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SimilarRecipesState.Loading,
    )

    fun updateFavouriteRecipe() {
        viewModelScope.launch {
            userDataRepository.setRecipeAddedToFavourite(
                recipeId.toString(),
                !isAddedAsFavourite.value,
            )
        }
    }
}

sealed interface RecipeDetailsState {
    data object Loading : RecipeDetailsState
    data class Success(val data: RecipeDetails) : RecipeDetailsState
    data class Error(val message: String) : RecipeDetailsState
}

sealed interface NutritionDetailsState {
    data object Loading : NutritionDetailsState
    data class Success(val data: NutritionDetails) : NutritionDetailsState
    data class Error(val message: String) : NutritionDetailsState
}

sealed interface SimilarRecipesState {
    data object Loading : SimilarRecipesState
    data object Empty : SimilarRecipesState
    data class Success(val data: List<Recipe>) : SimilarRecipesState
    data class Error(val message: String) : SimilarRecipesState
}