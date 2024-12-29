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

package com.niyaj.core.network.model

import com.niyaj.core.network.model.details.ExtendedIngredientResponse
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(

    val id: Int,

    val title: String,

    val readyInMinutes: Int,

    val servings: Int,

    val sourceUrl: String,

    val image: String = "",

    val imageType: String = "",

    val pricePerServing: Double? = null,

    val summary: String,

    val vegetarian: Boolean? = null,

    val vegan: Boolean? = null,

    val glutenFree: Boolean? = null,

    val dairyFree: Boolean? = null,

    val veryHealthy: Boolean? = null,

    val cheap: Boolean? = null,

    val veryPopular: Boolean? = null,

    val sustainable: Boolean? = null,

    val lowFodmap: Boolean? = null,

    val weightWatcherSmartPoints: Int? = null,

    val gaps: String? = null,

    val preparationMinutes: Int? = null,

    val cookingMinutes: Int? = null,

    val aggregateLikes: Int? = null,

    val healthScore: Int? = null,

    val creditsText: String? = null,

    val license: String? = null,

    val sourceName: String? = null,

    val extendedIngredients: List<ExtendedIngredientResponse>? = null,

    val cuisines: List<String>? = null,

    val dishTypes: List<String>? = null,

    val diets: List<String>? = null,

    val occasions: List<String>? = null,

    val instructions: String? = null,

    val analyzedInstructions: List<AnalyzedInstructionsResponse> = emptyList(),

    val originalId: String? = null,

    val spoonacularScore: Double? = null,

    val spoonacularSourceUrl: String? = null,
)
