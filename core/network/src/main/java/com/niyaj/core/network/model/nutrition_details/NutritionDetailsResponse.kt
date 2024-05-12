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

package com.niyaj.core.network.model.nutrition_details


import com.niyaj.core.network.model.details.CaloricBreakdownResponse
import com.niyaj.core.network.model.details.FlavonoidResponse
import com.niyaj.core.network.model.details.IngredientResponse
import com.niyaj.core.network.model.details.NutrientXResponse
import com.niyaj.core.network.model.details.PropertyResponse
import com.niyaj.core.network.model.details.WeightPerServingResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NutritionDetailsResponse(
    @Json(name = "bad")
    val bad: List<HealthResponse> = listOf(),

    @Json(name = "caloricBreakdown")
    val caloricBreakdown: CaloricBreakdownResponse = CaloricBreakdownResponse(),

    @Json(name = "calories")
    val calories: String = "",

    @Json(name = "carbs")
    val carbs: String = "",

    @Json(name = "expires")
    val expires: Long = 0,

    @Json(name = "fat")
    val fat: String = "",

    @Json(name = "flavonoids")
    val flavonoids: List<FlavonoidResponse> = listOf(),

    @Json(name = "good")
    val good: List<HealthResponse> = listOf(),

    @Json(name = "ingredients")
    val ingredients: List<IngredientResponse> = listOf(),

    @Json(name = "isStale")
    val isStale: Boolean = false,

    @Json(name = "nutrients")
    val nutrients: List<NutrientXResponse> = listOf(),

    @Json(name = "properties")
    val properties: List<PropertyResponse> = listOf(),

    @Json(name = "protein")
    val protein: String = "",

    @Json(name = "weightPerServing")
    val weightPerServing: WeightPerServingResponse = WeightPerServingResponse()
)