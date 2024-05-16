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

import androidx.compose.ui.graphics.vector.ImageVector
import com.niyaj.core.designsystem.icon.RecipeAppIcons
import com.niyaj.feature.favourite.R as favR
import com.niyaj.feature.home.R as homeR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = RecipeAppIcons.Home,
        unselectedIcon = RecipeAppIcons.OutlinedHome,
        iconTextId = homeR.string.feature_home_title,
        titleTextId = homeR.string.feature_home_app_name,
    ),
    FAVOURITE(
        selectedIcon = RecipeAppIcons.Favorite,
        unselectedIcon = RecipeAppIcons.OutlinedFavorite,
        iconTextId = favR.string.feature_favourite_title,
        titleTextId = favR.string.feature_favourite_app_name,
    ),
}
