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

import com.niyaj.core.model.Recipe
import com.niyaj.core.model.SimilarRecipe
import com.niyaj.core.network.model.SimilarRecipeResponse

fun List<com.niyaj.core.network.model.SimilarRecipeResponse>.toSimilarRecipes(): List<com.niyaj.core.model.SimilarRecipe> {
    return map {
        com.niyaj.core.model.SimilarRecipe(
            id = it.id,
            title = it.title,
            imageType = it.imageType,
            readyInMinutes = it.readyInMinutes,
            servings = it.servings,
            sourceUrl = it.sourceUrl,
        )
    }
}

fun List<com.niyaj.core.network.model.SimilarRecipeResponse>.toRecipes(): List<com.niyaj.core.model.Recipe> {
    return map {
        com.niyaj.core.model.Recipe(
            id = it.id,
            title = it.title,
            image = it.sourceUrl ?: "",
            imageType = it.imageType ?: "",
            servings = it.servings,
            readyInMinutes = it.readyInMinutes,
        )
    }
}