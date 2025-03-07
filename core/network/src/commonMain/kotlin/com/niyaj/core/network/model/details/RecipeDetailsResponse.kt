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

package com.niyaj.core.network.model.details


import com.niyaj.core.network.model.AnalyzedInstructionsResponse
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailsResponse(

    val aggregateLikes: Int = 0,

    val analyzedInstructions: List<AnalyzedInstructionsResponse> = listOf(),

    val cheap: Boolean = false,

    val cookingMinutes: Int = 0,

    val creditsText: String = "",

    val cuisines: List<String> = listOf(),

    val dairyFree: Boolean = false,

    val diets: List<String> = listOf(),

    val dishTypes: List<String> = listOf(),

    val extendedIngredients: List<ExtendedIngredientResponse> = listOf(),

    val gaps: String = "",

    val glutenFree: Boolean = false,

    val healthScore: Int = 0,

    val id: Int = 0,

    val image: String = "",

    val imageType: String = "",

    val instructions: String = "",

    val license: String = "",

    val lowFodmap: Boolean = false,

    val nutrition: NutritionResponse = NutritionResponse(),

    val occasions: List<String> = listOf(),

    val originalId: String? = null,

    val preparationMinutes: Int = 0,

    val pricePerServing: Double = 0.0,

    val readyInMinutes: Int = 0,

    val servings: Int = 0,

    val sourceName: String = "",

    val sourceUrl: String = "",

    val spoonacularScore: Double = 0.0,

    val spoonacularSourceUrl: String = "",

    val summary: String = "",

    val sustainable: Boolean = false,

    val taste: TasteResponse = TasteResponse(),

    val title: String = "",

    val vegan: Boolean = false,

    val vegetarian: Boolean = false,

    val veryHealthy: Boolean = false,

    val veryPopular: Boolean = false,

    val weightWatcherSmartPoints: Int = 0,

    val winePairing: WinePairingResponse = WinePairingResponse(),
)