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

package com.niyaj.feature.recipe_details.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.navigation.bottomSheet
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.niyaj.feature.recipe_details.RecipeDetailsRoute
import com.niyaj.feature.recipe_details.RecipeSheetScreenRoute

const val RECIPE_ID_ARG = "recipeId"
const val DETAILS_ROUTE_BASE = "recipe_details_route"
const val SHEET_ROUTE_BASE = "recipe_details_sheet_route"

const val DETAILS_ROUTE = "$DETAILS_ROUTE_BASE?$RECIPE_ID_ARG={$RECIPE_ID_ARG}"
const val SHEET_ROUTE = "$SHEET_ROUTE_BASE?$RECIPE_ID_ARG={$RECIPE_ID_ARG}"

fun NavController.navigateToRecipeDetails(recipeId: Int? = null, navOptions: NavOptions? = null) {
    val route = if (recipeId != null) {
        "${DETAILS_ROUTE_BASE}?${RECIPE_ID_ARG}=${recipeId}"
    } else {
        DETAILS_ROUTE_BASE
    }
    navigate(route, navOptions)
}

fun NavController.navigateToRecipeSheetDetails(recipeId: Int? = null, navOptions: NavOptions? = null) {
    val route = if (recipeId != null) {
        "${SHEET_ROUTE_BASE}?${RECIPE_ID_ARG}=${recipeId}"
    } else {
        SHEET_ROUTE_BASE
    }
    navigate(route, navOptions)
}

fun NavGraphBuilder.recipeDetailsScreen(
    onClickBack: () -> Unit,
) {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(RECIPE_ID_ARG) {
                defaultValue = null
                nullable = true
                type = NavType.StringType
            },
        ),
    ) {
        RecipeDetailsRoute(onClickBack)
    }
}


fun NavGraphBuilder.recipeDetailsSheetScreen(
    onClickBack: () -> Unit,
) {
    bottomSheet(
        route = SHEET_ROUTE,
        arguments = listOf(
            navArgument(RECIPE_ID_ARG) {
                defaultValue = null
                nullable = true
                type = NavType.StringType
            },
        ),
    ) {
        RecipeSheetScreenRoute(onClickBack)
    }
}