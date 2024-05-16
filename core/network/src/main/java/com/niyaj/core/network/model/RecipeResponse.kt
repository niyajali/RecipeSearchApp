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
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "readyInMinutes")
    val readyInMinutes: Int,

    @field:Json(name = "servings")
    val servings: Int,

    @field:Json(name = "sourceUrl")
    val sourceUrl: String,

    @field:Json(name = "image")
    val image: String = "",

    @field:Json(name = "imageType")
    val imageType: String = "",

    @field:Json(name = "pricePerServing")
    val pricePerServing: Double? = null,

    @field:Json(name = "summary")
    val summary: String,

    @field:Json(name = "vegetarian")
    val vegetarian: Boolean? = null,

    @field:Json(name = "vegan")
    val vegan: Boolean? = null,

    @field:Json(name = "glutenFree")
    val glutenFree: Boolean? = null,

    @field:Json(name = "dairyFree")
    val dairyFree: Boolean? = null,

    @field:Json(name = "veryHealthy")
    val veryHealthy: Boolean? = null,

    @field:Json(name = "cheap")
    val cheap: Boolean? = null,

    @field:Json(name = "veryPopular")
    val veryPopular: Boolean? = null,

    @field:Json(name = "sustainable")
    val sustainable: Boolean? = null,

    @field:Json(name = "lowFodmap")
    val lowFodmap: Boolean? = null,

    @field:Json(name = "weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null,

    @field:Json(name = "gaps")
    val gaps: String? = null,

    @field:Json(name = "preparationMinutes")
    val preparationMinutes: Int? = null,

    @field:Json(name = "cookingMinutes")
    val cookingMinutes: Int? = null,

    @field:Json(name = "aggregateLikes")
    val aggregateLikes: Int? = null,

    @field:Json(name = "healthScore")
    val healthScore: Int? = null,

    @field:Json(name = "creditsText")
    val creditsText: String? = null,

    @field:Json(name = "license")
    val license: String? = null,

    @field:Json(name = "sourceName")
    val sourceName: String? = null,

    @field:Json(name = "extendedIngredients")
    val extendedIngredients: List<ExtendedIngredientResponse>? = null,

    @field:Json(name = "cuisines")
    val cuisines: List<String>? = null,

    @field:Json(name = "dishTypes")
    val dishTypes: List<String>? = null,

    @field:Json(name = "diets")
    val diets: List<String>? = null,

    @field:Json(name = "occasions")
    val occasions: List<String>? = null,

    @field:Json(name = "instructions")
    val instructions: String? = null,

    @field:Json(name = "analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstructionsResponse> = emptyList(),

    @field:Json(name = "originalId")
    val originalId: String? = null,

    @field:Json(name = "spoonacularScore")
    val spoonacularScore: Double? = null,

    @field:Json(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null,
)
