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

package com.niyaj.feature.recipe_details

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niyaj.core.data.model.toRecipeBasicDetail
import com.niyaj.core.designsystem.icon.RecipeAppIcons
import com.niyaj.core.ui.LoadingIndicator
import com.niyaj.core.ui.StandardErrorMessage
import com.niyaj.feature.recipe_details.components.NutritionDetailsCard
import com.niyaj.feature.recipe_details.components.RecipeBasicDetails
import com.niyaj.feature.recipe_details.components.RecipeIngredientsCard
import com.niyaj.feature.recipe_details.components.RecipeTextCard

@Composable
fun RecipeDetailsRoute(
    onClickBack: () -> Unit,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.recipeDetails.collectAsStateWithLifecycle()
    val nutritionUiState by viewModel.nutritionDetails.collectAsStateWithLifecycle()
    val isAddedAsFavourite by viewModel.isAddedAsFavourite.collectAsStateWithLifecycle()

    BackHandler(onBack = onClickBack)

    RecipeDetailsScreen(
        uiState = uiState,
        nutritionUiState = nutritionUiState,
        isAddedAsFavourite = isAddedAsFavourite,
        onClickAddFavorite = viewModel::updateFavouriteRecipe,
    )
}

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailsState,
    nutritionUiState: NutritionDetailsState,
    isAddedAsFavourite: Boolean,
    onClickAddFavorite: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Crossfade(
            targetState = uiState,
            label = "RecipeDetails::state",
        ) { state ->
            when (state) {
                is RecipeDetailsState.Loading -> LoadingIndicator()

                is RecipeDetailsState.Error -> {
                    StandardErrorMessage(message = state.message)
                }

                is RecipeDetailsState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        item {
                            RecipeBasicDetails(
                                recipe = state.data.toRecipeBasicDetail(),
                            )
                        }

                        item {
                            RecipeIngredientsCard(
                                title = "Ingredients",
                                data = state.data.analyzedInstructions.flatMap { list ->
                                    list.steps.flatMap { it.ingredients }
                                },
                            )
                        }

                        item {
                            RecipeTextCard(
                                title = "Instructions",
                                bodyText = state.data.instructions,
                            )
                        }

                        item {
                            RecipeIngredientsCard(
                                title = "Equipments",
                                data = state.data.analyzedInstructions.flatMap { instructions ->
                                    instructions.steps.flatMap { it.equipment }
                                },
                            )
                        }

                        item {
                            RecipeTextCard(
                                title = "Quick Summary",
                                bodyText = state.data.summary,
                            )
                        }

                        item {
                            NutritionDetailsCard(
                                nutritionDetailsState = nutritionUiState,
                            )
                        }
                    }
                }
            }
        }

        FilledIconButton(
            onClick = onClickAddFavorite,
            shape = CircleShape,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier
                .padding(top = 48.dp, end = 16.dp)
                .align(Alignment.TopEnd),
        ) {
            Icon(
                imageVector = if (isAddedAsFavourite) RecipeAppIcons.Favorite
                else RecipeAppIcons.OutlinedFavorite,
                contentDescription = "Add to favourite",
            )
        }
    }
}


