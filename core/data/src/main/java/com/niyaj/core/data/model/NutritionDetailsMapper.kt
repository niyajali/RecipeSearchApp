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

package com.niyaj.core.data.model

import com.niyaj.core.model.nutrition_details.HealthDetail
import com.niyaj.core.model.nutrition_details.NutritionDetails
import com.niyaj.core.network.model.nutrition_details.HealthResponse
import com.niyaj.core.network.model.nutrition_details.NutritionDetailsResponse

fun List<HealthResponse>.toGoodHealthList(): List<com.niyaj.core.model.nutrition_details.HealthDetail> {
    return map {
        com.niyaj.core.model.nutrition_details.HealthDetail(
            amount = it.amount,
            indented = it.indented,
            percentOfDailyNeeds = it.percentOfDailyNeeds,
            title = it.title,
        )
    }
}

fun List<HealthResponse>.toBadHealthList(): List<com.niyaj.core.model.nutrition_details.HealthDetail> {
    return map {
        com.niyaj.core.model.nutrition_details.HealthDetail(
            amount = it.amount,
            indented = it.indented,
            percentOfDailyNeeds = it.percentOfDailyNeeds,
            title = it.title,
        )
    }
}

fun NutritionDetailsResponse.toNutritionDetails(): com.niyaj.core.model.nutrition_details.NutritionDetails {
    return com.niyaj.core.model.nutrition_details.NutritionDetails(
        bad = bad.toBadHealthList(),
        caloricBreakdown = caloricBreakdown.toCaloricBreakdownModel(),
        calories = calories,
        carbs = carbs,
        expires = expires,
        fat = fat,
        flavonoids = flavonoids.toFlavonoidList(),
        good = good.toGoodHealthList(),
        ingredients = ingredients.toIngredientList(),
        isStale = isStale,
        nutrients = nutrients.toNutrientXList(),
        properties = properties.toPropertyList(),
        protein = protein,
        weightPerServing = weightPerServing.toWeightPerServingModel()
    )
}