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
import kotlinx.serialization.Serializable


@Serializable
data class NutritionDetailsResponse(
    val bad: List<HealthResponse> = listOf(),

    val caloricBreakdown: CaloricBreakdownResponse = CaloricBreakdownResponse(),

    val calories: String = "",

    val carbs: String = "",

    val expires: Long = 0,

    val fat: String = "",

    val flavonoids: List<FlavonoidResponse> = listOf(),

    val good: List<HealthResponse> = listOf(),

    val ingredients: List<IngredientResponse> = listOf(),

    val isStale: Boolean = false,

    val nutrients: List<NutrientXResponse> = listOf(),

    val properties: List<PropertyResponse> = listOf(),

    val protein: String = "",

    val weightPerServing: WeightPerServingResponse = WeightPerServingResponse(),
)