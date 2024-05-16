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

package com.niyaj.core.network.service

import com.niyaj.core.network.model.RecipesResponse
import com.niyaj.core.network.model.SearchResponse
import com.niyaj.core.network.model.SimilarRecipeResponse
import com.niyaj.core.network.model.details.RecipeDetailsResponse
import com.niyaj.core.network.model.nutrition_details.NutritionDetailsResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class RecipeClient @Inject constructor(
    private val recipeService: RecipeService,
) {
    suspend fun getRandomRecipes(limit: Int): ApiResponse<RecipesResponse> {
        return recipeService.getRandomRecipes(limit)
    }

    suspend fun searchRecipe(query: String): ApiResponse<SearchResponse> {
        return recipeService.searchRecipe(query)
    }

    suspend fun getRecipeDetailsById(
        recipeId: Int,
        includeNutrition: Boolean,
    ): ApiResponse<RecipeDetailsResponse> {
        return recipeService.getRecipeDetails(recipeId, includeNutrition)
    }

    suspend fun getNutritionDetails(recipeId: Int): ApiResponse<NutritionDetailsResponse> {
        return recipeService.getNutritionDetails(recipeId)
    }

    suspend fun getSimilarRecipes(recipeId: Int, limit: Int): ApiResponse<List<SimilarRecipeResponse>> {
        return recipeService.getSimilarRecipes(recipeId, limit)
    }
}