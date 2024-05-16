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
import com.niyaj.core.network.model.RecipeResponse
import com.niyaj.core.network.model.RecipesResponse

fun RecipesResponse.toRecipes(): List<Recipe> {
    return this.recipes.map {
        it.toRecipeModel()
    }
}

fun RecipeResponse.toRecipeModel(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        image = this.image,
        imageType = this.imageType,
        servings = this.servings,
        readyInMinutes = this.readyInMinutes,
        pricePerServing = this.pricePerServing,
    )
}