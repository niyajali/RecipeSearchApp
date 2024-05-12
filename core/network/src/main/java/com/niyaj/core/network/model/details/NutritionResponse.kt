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


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NutritionResponse(
    @Json(name = "caloricBreakdown")
    val caloricBreakdown: CaloricBreakdownResponse = CaloricBreakdownResponse(),

    @Json(name = "flavonoids")
    val flavonoids: List<FlavonoidResponse> = listOf(),

    @Json(name = "ingredients")
    val ingredients: List<IngredientResponse> = listOf(),

    @Json(name = "nutrients")
    val nutrients: List<NutrientXResponse> = listOf(),

    @Json(name = "properties")
    val properties: List<PropertyResponse> = listOf(),

    @Json(name = "weightPerServing")
    val weightPerServing: WeightPerServingResponse = WeightPerServingResponse()
)