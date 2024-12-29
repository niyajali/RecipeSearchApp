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

package com.niyaj.core.model.details


data class Nutrition(
    val caloricBreakdown: com.niyaj.core.model.details.CaloricBreakdown = com.niyaj.core.model.details.CaloricBreakdown(),

    val flavonoids: List<com.niyaj.core.model.details.Flavonoid> = listOf(),

    val ingredients: List<com.niyaj.core.model.details.Ingredient> = listOf(),

    val nutrients: List<com.niyaj.core.model.details.NutrientX> = listOf(),

    val properties: List<com.niyaj.core.model.details.Property> = listOf(),

    val weightPerServing: com.niyaj.core.model.details.WeightPerServing = com.niyaj.core.model.details.WeightPerServing()
)