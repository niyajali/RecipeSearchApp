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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
    ): ApiResponse<RecipesResponse>


    @GET("recipes/complexSearch")
    suspend fun searchRecipe(
        @Query("query") query: String
    ): ApiResponse<SearchResponse>


    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean,
    ): ApiResponse<RecipeDetailsResponse>

    @GET("recipes/{id}/nutritionWidget.json")
    suspend fun getNutritionDetails(
        @Path("id") id: Int,
    ): ApiResponse<NutritionDetailsResponse>

    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id") id: Int,
        @Query("number") number: Int,
    ): ApiResponse<List<SimilarRecipeResponse>>
}

