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
import com.niyaj.core.model.details.AnalyzedInstructions
import com.niyaj.core.model.details.CaloricBreakdown
import com.niyaj.core.model.details.ExtendedIngredient
import com.niyaj.core.model.details.Flavonoid
import com.niyaj.core.model.details.Ingredient
import com.niyaj.core.model.details.Ingredients
import com.niyaj.core.model.details.Measures
import com.niyaj.core.model.details.Metric
import com.niyaj.core.model.details.NutrientX
import com.niyaj.core.model.details.Nutrition
import com.niyaj.core.model.details.ProductMatches
import com.niyaj.core.model.details.Property
import com.niyaj.core.model.details.RecipeDetails
import com.niyaj.core.model.details.Steps
import com.niyaj.core.model.details.Taste
import com.niyaj.core.model.details.UnitDetail
import com.niyaj.core.model.details.WeightPerServing
import com.niyaj.core.model.details.WinePairing
import com.niyaj.core.network.model.AnalyzedInstructionsResponse
import com.niyaj.core.network.model.IngredientsResponse
import com.niyaj.core.network.model.StepsResponse
import com.niyaj.core.network.model.details.CaloricBreakdownResponse
import com.niyaj.core.network.model.details.ExtendedIngredientResponse
import com.niyaj.core.network.model.details.FlavonoidResponse
import com.niyaj.core.network.model.details.IngredientResponse
import com.niyaj.core.network.model.details.MeasuresResponse
import com.niyaj.core.network.model.details.MetricResponse
import com.niyaj.core.network.model.details.NutrientXResponse
import com.niyaj.core.network.model.details.NutritionResponse
import com.niyaj.core.network.model.details.ProductMatchesResponse
import com.niyaj.core.network.model.details.PropertyResponse
import com.niyaj.core.network.model.details.RecipeDetailsResponse
import com.niyaj.core.network.model.details.TasteResponse
import com.niyaj.core.network.model.details.UnitResponse
import com.niyaj.core.network.model.details.WeightPerServingResponse
import com.niyaj.core.network.model.details.WinePairingResponse


fun UnitResponse.toUnitModel(): UnitDetail {
    return UnitDetail(
        amount = amount,
        unitLong = unitLong,
        unitShort = unitShort,
    )
}

fun MetricResponse.toMetricModel(): Metric {
    return Metric(
        amount = amount,
        unitLong = unitLong,
        unitShort = unitShort,
    )
}

fun MeasuresResponse.toMeasuresModel(): Measures {
    return Measures(
        us = us.toUnitModel(),
        metric = metric.toMetricModel(),
    )
}

fun CaloricBreakdownResponse.toCaloricBreakdownModel(): CaloricBreakdown {
    return CaloricBreakdown(
        percentProtein = percentProtein,
        percentFat = percentFat,
        percentCarbs = percentCarbs,
    )
}

fun WeightPerServingResponse.toWeightPerServingModel(): WeightPerServing {
    return WeightPerServing(
        amount = amount,
        unit = unit,
    )
}

fun NutritionResponse.toNutritionModel(): Nutrition {
    return Nutrition(
        caloricBreakdown = caloricBreakdown.toCaloricBreakdownModel(),
        flavonoids = flavonoids.toFlavonoidList(),
        ingredients = ingredients.toIngredientList(),
        nutrients = nutrients.toNutrientXList(),
        properties = properties.toPropertyList(),
        weightPerServing = weightPerServing.toWeightPerServingModel(),
    )
}

fun TasteResponse.toTasteModel(): Taste {
    return Taste(
        bitterness = bitterness,
        fattiness = fattiness,
        saltiness = saltiness,
        savoriness = savoriness,
        sourness = sourness,
        spiciness = spiciness,
        sweetness = sweetness,
    )
}

fun WinePairingResponse.toWinePairingModel(): WinePairing {
    return WinePairing(
        pairedWines = pairedWines,
        pairingText = pairingText,
        productMatches = productMatches.toProductMatchesList(),
    )

}

fun List<ExtendedIngredientResponse>.toExtendedIngredientList(): List<ExtendedIngredient> {
    return this.map {
        ExtendedIngredient(
            aisle = it.aisle,
            amount = it.amount,
            consistency = it.consistency,
            id = it.id,
            image = it.image,
            measures = it.measures?.toMeasuresModel(),
            meta = it.meta,
            name = it.name,
            nameClean = it.nameClean,
            original = it.original,
            originalName = it.originalName,
            unit = it.unit,
        )
    }
}

fun List<IngredientsResponse>.toIngredientsList(): List<Ingredients> {
    return this.map {
        Ingredients(
            id = it.id,
            name = it.name,
            localizedName = it.localizedName,
            image = it.image,
        )
    }
}

fun List<StepsResponse>.toStepsList(): List<Steps> {
    return this.map {
        Steps(
            number = it.number,
            step = it.step,
            ingredients = it.ingredients.toIngredientsList(),
            equipment = it.equipment.toIngredientsList(),
        )
    }
}

fun List<AnalyzedInstructionsResponse>.toAnalyzedInstructions(): List<AnalyzedInstructions> {
    return this.map {
        AnalyzedInstructions(
            name = it.name,
            steps = it.steps.toStepsList(),
        )
    }
}

fun List<FlavonoidResponse>.toFlavonoidList(): List<Flavonoid> {
    return this.map {
        Flavonoid(
            amount = it.amount,
            name = it.name,
            unit = it.unit,
        )
    }
}

fun List<NutrientXResponse>.toNutrientXList(): List<NutrientX> {
    return this.map {
        NutrientX(
            amount = it.amount,
            name = it.name,
            unit = it.unit,
            percentOfDailyNeeds = it.percentOfDailyNeeds,
        )
    }
}

fun List<IngredientResponse>.toIngredientList(): List<Ingredient> {
    return this.map {
        Ingredient(
            amount = it.amount,
            id = it.id,
            name = it.name,
            nutrients = it.nutrients.toNutrientXList(),
            unit = it.unit
        )
    }
}

fun List<PropertyResponse>.toPropertyList(): List<Property> {
    return this.map {
        Property(
            name = it.name,
            amount = it.amount,
            unit = it.unit,
        )
    }
}

fun List<ProductMatchesResponse>.toProductMatchesList(): List<ProductMatches> {
    return this.map {
        ProductMatches(
            averageRating = it.averageRating,
            description = it.description,
            id = it.id,
            imageUrl = it.imageUrl,
            link = it.link,
            price = it.price,
            ratingCount = it.ratingCount,
            score = it.score,
            title = it.title,
        )
    }
}

fun RecipeDetailsResponse.toRecipeDetail(): RecipeDetails {
    return RecipeDetails(
        aggregateLikes = aggregateLikes,
        analyzedInstructions = analyzedInstructions.toAnalyzedInstructions(),
        cheap = cheap,
        cookingMinutes = cookingMinutes,
        creditsText = creditsText,
        cuisines = cuisines,
        dairyFree = dairyFree,
        diets = diets,
        dishTypes = dishTypes,
        extendedIngredients = extendedIngredients.toExtendedIngredientList(),
        gaps = gaps,
        glutenFree = glutenFree,
        healthScore = healthScore,
        id = id,
        image = image,
        imageType = imageType,
        instructions = instructions,
        license = license,
        lowFodmap = lowFodmap,
        nutrition = nutrition.toNutritionModel(),
        occasions = occasions,
        originalId = originalId,
        preparationMinutes = preparationMinutes,
        pricePerServing = pricePerServing,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        spoonacularScore = spoonacularScore,
        spoonacularSourceUrl = spoonacularSourceUrl,
        summary = summary,
        sustainable = sustainable,
        taste = taste.toTasteModel(),
        title = title,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        veryPopular = veryPopular,
        weightWatcherSmartPoints = weightWatcherSmartPoints,
        winePairing = winePairing.toWinePairingModel(),
    )
}

fun RecipeDetails.toRecipeBasicDetail(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image,
        imageType = imageType,
        servings = servings,
        readyInMinutes = readyInMinutes,
        pricePerServing = pricePerServing,
    )
}

fun RecipeDetailsResponse.toRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image,
        imageType = imageType,
        servings = servings,
        readyInMinutes = readyInMinutes,
        pricePerServing = pricePerServing
    )
}