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

package com.niyaj.recipesearchapp.navigation

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.niyaj.core.designsystem.theme.Gray0
import com.niyaj.feature.account.login.navigation.LOGIN_SCREEN_ROUTE
import com.niyaj.feature.account.login.navigation.loginScreen
import com.niyaj.feature.favourite.navigation.favouriteScreen
import com.niyaj.feature.home.navigation.homeScreen
import com.niyaj.feature.recipe_details.navigation.navigateToRecipeDetails
import com.niyaj.feature.recipe_details.navigation.navigateToRecipeSheetDetails
import com.niyaj.feature.recipe_details.navigation.recipeDetailsScreen
import com.niyaj.feature.recipe_details.navigation.recipeDetailsSheetScreen
import com.niyaj.feature.search.navigation.navigateToSearch
import com.niyaj.feature.search.navigation.searchScreen
import com.niyaj.recipesearchapp.ui.RecipeAppState

@Composable
fun NiaNavHost(
    appState: RecipeAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = LOGIN_SCREEN_ROUTE,
) {
    val navController = appState.navController

    ModalBottomSheetLayout(
        bottomSheetNavigator = appState.bottomSheetNavigator,
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetBackgroundColor = Gray0,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            loginScreen(
                onLoginClick = {
                    appState.navigateToTopLevelDestination(TopLevelDestination.HOME)
                },
            )

            homeScreen(
                onClickSearch = navController::navigateToSearch,
                onClickRecipe = navController::navigateToRecipeDetails,
            )

            searchScreen(
                onBackClick = navController::popBackStack,
                onSearchItemClick = navController::navigateToRecipeSheetDetails,
            )

            recipeDetailsScreen(
                onClickBack = navController::popBackStack,
            )

            favouriteScreen(
                onClickRecipe = navController::navigateToRecipeDetails,
            )

            recipeDetailsSheetScreen(
                onClickBack = navController::popBackStack
            )
        }
    }
}