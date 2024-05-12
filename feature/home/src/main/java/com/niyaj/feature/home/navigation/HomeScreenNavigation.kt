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

package com.niyaj.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.niyaj.feature.home.HomeScreenRoute

const val HOME_SCREEN_ROUTE = "home_screen"

fun NavController.navigateToHomeScreen(navOptions: NavOptions) =
    navigate(HOME_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    onClickSearch: () -> Unit,
    onClickRecipe: (Int) -> Unit,
) {
    composable(route = HOME_SCREEN_ROUTE) {
        HomeScreenRoute(onClickSearch, onClickRecipe)
    }
}