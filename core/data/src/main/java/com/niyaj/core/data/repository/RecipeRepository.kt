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

package com.niyaj.core.data.repository

import androidx.annotation.WorkerThread
import com.niyaj.core.common.result.Result
import com.niyaj.core.model.Recipe
import com.niyaj.core.model.SearchResult
import com.niyaj.core.model.details.RecipeDetails
import com.niyaj.core.model.nutrition_details.NutritionDetails
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    @WorkerThread
    suspend fun getAllRandomRecipes(limit: Int): Flow<Result<List<Recipe>>>

    suspend fun searchRecipes(query: String): Flow<Result<List<SearchResult>>>

    suspend fun getRecipeDetails(recipeId: Int, includeNutrition: Boolean): Flow<Result<RecipeDetails>>

    suspend fun getNutritionDetails(recipeId: Int): Flow<Result<NutritionDetails>>

    suspend fun getFavouriteRecipes(recipesIds: List<String>): Flow<Result<List<Recipe>>>

    suspend fun getSimilarRecipes(recipeId: Int, limit: Int): Flow<Result<List<Recipe>>>
}