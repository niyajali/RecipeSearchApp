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
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDetailsResponse(

    @Json(name = "aggregateLikes")
    val aggregateLikes: Int = 0,

    @Json(name = "analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstructionsResponse> = listOf(),

    @Json(name = "cheap")
    val cheap: Boolean = false,

    @Json(name = "cookingMinutes")
    val cookingMinutes: Int = 0,

    @Json(name = "creditsText")
    val creditsText: String = "",

    @Json(name = "cuisines")
    val cuisines: List<String> = listOf(),

    @Json(name = "dairyFree")
    val dairyFree: Boolean = false,

    @Json(name = "diets")
    val diets: List<String> = listOf(),

    @Json(name = "dishTypes")
    val dishTypes: List<String> = listOf(),

    @Json(name = "extendedIngredients")
    val extendedIngredients: List<ExtendedIngredientResponse> = listOf(),

    @Json(name = "gaps")
    val gaps: String = "",

    @Json(name = "glutenFree")
    val glutenFree: Boolean = false,

    @Json(name = "healthScore")
    val healthScore: Int = 0,

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "image")
    val image: String = "",

    @Json(name = "imageType")
    val imageType: String = "",

    @Json(name = "instructions")
    val instructions: String = "",

    @Json(name = "license")
    val license: String = "",

    @Json(name = "lowFodmap")
    val lowFodmap: Boolean = false,

    @Json(name = "nutrition")
    val nutrition: NutritionResponse = NutritionResponse(),

    @Json(name = "occasions")
    val occasions: List<String> = listOf(),

    @Json(name = "originalId")
    val originalId: String? = null,

    @Json(name = "preparationMinutes")
    val preparationMinutes: Int = 0,

    @Json(name = "pricePerServing")
    val pricePerServing: Double = 0.0,

    @Json(name = "readyInMinutes")
    val readyInMinutes: Int = 0,

    @Json(name = "servings")
    val servings: Int = 0,

    @Json(name = "sourceName")
    val sourceName: String = "",

    @Json(name = "sourceUrl")
    val sourceUrl: String = "",

    @Json(name = "spoonacularScore")
    val spoonacularScore: Double = 0.0,

    @Json(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String = "",

    @Json(name = "summary")
    val summary: String = "",

    @Json(name = "sustainable")
    val sustainable: Boolean = false,

    @Json(name = "taste")
    val taste: TasteResponse = TasteResponse(),

    @Json(name = "title")
    val title: String = "",

    @Json(name = "vegan")
    val vegan: Boolean = false,

    @Json(name = "vegetarian")
    val vegetarian: Boolean = false,

    @Json(name = "veryHealthy")
    val veryHealthy: Boolean = false,

    @Json(name = "veryPopular")
    val veryPopular: Boolean = false,

    @Json(name = "weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int = 0,

    @Json(name = "winePairing")
    val winePairing: WinePairingResponse = WinePairingResponse()
)