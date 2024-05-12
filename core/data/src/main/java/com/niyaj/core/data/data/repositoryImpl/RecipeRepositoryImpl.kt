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

import android.util.Log
import androidx.annotation.WorkerThread
import com.niyaj.core.common.network.Dispatcher
import com.niyaj.core.common.network.RecipeAppDispatchers
import com.niyaj.core.data.model.toNutritionDetails
import com.niyaj.core.data.model.toRecipe
import com.niyaj.core.data.model.toRecipeDetail
import com.niyaj.core.data.model.toRecipes
import com.niyaj.core.data.model.toSearchResult
import com.niyaj.core.data.repository.RecipeRepository
import com.niyaj.core.model.Recipe
import com.niyaj.core.model.SearchResult
import com.niyaj.core.model.details.RecipeDetails
import com.niyaj.core.model.nutrition_details.NutritionDetails
import com.niyaj.core.network.service.RecipeClient
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeClient: RecipeClient,
    @Dispatcher(RecipeAppDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher,
) : RecipeRepository {

    @WorkerThread
    override suspend fun getAllRandomRecipes(
        limit: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<Recipe>> = flow {
        recipeClient
            .getRandomRecipes(limit)
            .suspendOnSuccess {
                emit(this.data.toRecipes())
            }.onFailure {
                Log.d("Tag", "Error - ${message()}")
                onError(message())
            }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


    override suspend fun searchRecipes(query: String): Flow<List<SearchResult>> {
        return flow {
            val result = recipeClient.searchRecipe(query)

            result.suspendOnSuccess {
                emit(this.data.toSearchResult())
            }.onFailure {
                Log.d("Tag", message())

                throw Exception(message())
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecipeDetails(
        recipeId: Int,
        includeNutrition: Boolean,
        onError: (String?) -> Unit,
    ): Flow<RecipeDetails> {
        return flow {
            val result = recipeClient.getRecipeDetailsById(recipeId, includeNutrition)
            result.suspendOnSuccess {
                emit(this.data.toRecipeDetail())
            }.onFailure {
                Log.d("Tag", message())

                onError(message())
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getNutritionDetails(
        recipeId: Int,
        onError: (String?) -> Unit,
    ): Flow<NutritionDetails> {
        return flow {
            val result = recipeClient.getNutritionDetails(recipeId)

            result.suspendOnSuccess {
                emit(this.data.toNutritionDetails())
            }.onFailure {
                Log.d("Tag", message())

                onError(message())
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getFavouriteRecipes(
        recipesIds: List<String>,
        onError: (String?) -> Unit,
    ): Flow<List<Recipe>> {
        return flow {
            val data = recipesIds.map {
                recipeClient.getRecipeDetailsById(it.toInt(), false)
                    .onFailure {
                        Log.d("Tag", message())
                        onError(message())
                    }
            }.mapNotNull {
                it.getOrNull()?.toRecipe()
            }

            emit(data)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getSimilarRecipes(
        recipeId: Int,
        limit: Int,
        onError: (String?) -> Unit,
    ): Flow<List<Recipe>> {
        return flow<List<Recipe>> {
            val result = recipeClient.getSimilarRecipes(recipeId, limit)

            result.suspendOnSuccess {
                emit(data.toRecipes())
            }.onFailure {
                Log.d("Tag", message())
                onError(message())
            }
        }.flowOn(ioDispatcher)
    }
}