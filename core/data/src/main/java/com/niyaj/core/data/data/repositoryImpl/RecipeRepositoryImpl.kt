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
import com.niyaj.core.common.result.Result
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
import com.skydoves.sandwich.messageOrNull
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    ): Flow<Result<List<Recipe>>> = flow {
        recipeClient
            .getRandomRecipes(limit)
            .suspendOnSuccess {
                emit(Result.Success(this.data.toRecipes()))
            }.suspendOnFailure {
                Log.d("suspendOnFailure", "suspendOnFailure - ${this.messageOrNull}")
                emit(Result.Error(Throwable(message())))
            }
    }.onStart { emit(Result.Loading) }.flowOn(ioDispatcher)


    override suspend fun searchRecipes(query: String): Flow<Result<List<SearchResult>>> {
        return flow {
            val result = recipeClient.searchRecipe(query)

            result.suspendOnSuccess {
                emit(Result.Success(this.data.toSearchResult()))
            }.suspendOnFailure {
                Log.d("Tag", message())

                emit(Result.Error(Throwable(message())))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecipeDetails(
        recipeId: Int,
        includeNutrition: Boolean,
    ): Flow<Result<RecipeDetails>> {
        return flow {
            val result = recipeClient.getRecipeDetailsById(recipeId, includeNutrition)

            result.suspendOnSuccess {
                emit(Result.Success(this.data.toRecipeDetail()))
            }.suspendOnFailure {
                Log.d("Tag", message())

                emit(Result.Error(Throwable(message())))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getNutritionDetails(
        recipeId: Int,
    ): Flow<Result<NutritionDetails>> {
        return flow {
            val result = recipeClient.getNutritionDetails(recipeId)

            result.suspendOnSuccess {
                emit(Result.Success(this.data.toNutritionDetails()))
            }.suspendOnFailure {
                Log.d("Tag", message())

                emit(Result.Error(Throwable(message())))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getFavouriteRecipes(
        recipesIds: List<String>,
    ): Flow<Result<List<Recipe>>> {
        return flow {
            recipesIds.map {
                recipeClient.getRecipeDetailsById(it.toInt(), false)
                    .suspendOnFailure {
                        Log.d("Tag", message())

                        emit(Result.Error(Throwable(message())))
                    }
            }.mapNotNull {
                it.getOrNull()?.toRecipe()

            }.let {
                emit(Result.Success(it))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getSimilarRecipes(
        recipeId: Int,
        limit: Int,
    ): Flow<Result<List<Recipe>>> {
        return flow {
            val result = recipeClient.getSimilarRecipes(recipeId, limit)

            result.suspendOnSuccess {
                emit(Result.Success(data.toRecipes()))
            }.suspendOnFailure {
                Log.d("Tag", message())
                emit(Result.Error(Throwable(message())))
            }
        }.flowOn(ioDispatcher)
    }
}