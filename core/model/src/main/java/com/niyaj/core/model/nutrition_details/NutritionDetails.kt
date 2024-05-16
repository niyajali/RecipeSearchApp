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

package com.niyaj.core.model.nutrition_details


import com.niyaj.core.model.details.CaloricBreakdown
import com.niyaj.core.model.details.Flavonoid
import com.niyaj.core.model.details.Ingredient
import com.niyaj.core.model.details.NutrientX
import com.niyaj.core.model.details.Property
import com.niyaj.core.model.details.WeightPerServing


data class NutritionDetails(
    val bad: List<HealthDetail> = listOf(),

    val caloricBreakdown: CaloricBreakdown = CaloricBreakdown(),

    val calories: String = "",

    val carbs: String = "",

    val expires: Long = 0,

    val fat: String = "",

    val flavonoids: List<Flavonoid> = listOf(),

    val good: List<HealthDetail> = listOf(),

    val ingredients: List<Ingredient> = listOf(),

    val isStale: Boolean = false,

    val nutrients: List<NutrientX> = listOf(),

    val properties: List<Property> = listOf(),

    val protein: String = "",

    val weightPerServing: WeightPerServing = WeightPerServing(),
)