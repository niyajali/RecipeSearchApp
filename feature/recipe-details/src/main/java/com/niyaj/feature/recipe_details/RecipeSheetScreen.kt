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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niyaj.core.data.model.toRecipeBasicDetail
import com.niyaj.core.designsystem.component.PrimaryTextWithIconButton
import com.niyaj.core.designsystem.icon.RecipeAppIcons
import com.niyaj.core.model.Recipe
import com.niyaj.core.model.details.Ingredients
import com.niyaj.core.model.details.RecipeDetails
import com.niyaj.core.ui.LoadingIndicator
import com.niyaj.core.ui.RecipeCard
import com.niyaj.core.ui.StandardBottomSheet
import com.niyaj.core.ui.StandardErrorMessage
import com.niyaj.feature.recipe_details.components.ExpandableCard
import com.niyaj.feature.recipe_details.components.NutritionDetailsCard
import com.niyaj.feature.recipe_details.components.RecipeBasicDetails
import com.niyaj.feature.recipe_details.components.RecipeIngredientCard
import com.niyaj.feature.recipe_details.components.RecipeIngredientsCard
import com.niyaj.feature.recipe_details.components.RecipeTextCard

// TODO: Fix Bottom Sheet Height & Ui Placements

@Composable
fun RecipeSheetScreenRoute(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.recipeDetails.collectAsStateWithLifecycle().value
    val isAddedToFavourite = viewModel.isAddedAsFavourite.collectAsStateWithLifecycle().value
    val nutritionState = viewModel.nutritionDetails.collectAsStateWithLifecycle().value
    val similarRecipeState = viewModel.similarRecipes.collectAsStateWithLifecycle().value

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
    ) {
        when (uiState) {
            is RecipeDetailsState.Loading -> LoadingIndicator()
            is RecipeDetailsState.Error -> StandardErrorMessage(message = uiState.message)
            is RecipeDetailsState.Success -> {
                StandardBottomSheet(
                    title = uiState.data.title,
                    isAddedToFavourite = isAddedToFavourite,
                    onClickBack = onClickBack,
                    onClickFavourite = viewModel::updateFavouriteRecipe,
                    content = {
                        RecipeSheetScreen(
                            uiState = uiState.data,
                            nutritionState = nutritionState,
                            similarRecipesState = similarRecipeState,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    },
                )
            }
        }
    }
}


@Composable
fun RecipeSheetScreen(
    modifier: Modifier = Modifier,
    uiState: com.niyaj.core.model.details.RecipeDetails,
    nutritionState: NutritionDetailsState,
    similarRecipesState: SimilarRecipesState,
) {
    var currentState by remember { mutableStateOf(RecipeSheetState.BASIC_DETAILS) }

    var ingredientExpanded by remember { mutableStateOf(true) }

    var fullRecipeExpanded by remember { mutableStateOf(true) }

    var similarRecipeExpanded by remember { mutableStateOf(true) }

    val ingredients = uiState.analyzedInstructions.flatMap { list ->
        list.steps.flatMap { it.ingredients }
    }

    val equipments = uiState.analyzedInstructions.flatMap { list ->
        list.steps.flatMap { it.ingredients }
    }

    AnimatedContent(
        targetState = currentState,
        label = "",
        transitionSpec = {
            slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = Up,
            ).togetherWith(
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = Down,
                ),
            )
        },
    ) {
        when (it) {
            RecipeSheetState.BASIC_DETAILS -> {
                RecipeSheetBasicDetail(
                    modifier = modifier,
                    recipe = uiState.toRecipeBasicDetail(),
                    onClickBtn = { currentState = RecipeSheetState.INGREDIENTS },
                )
            }

            RecipeSheetState.INGREDIENTS -> {
                RecipeIngredientsCard(
                    modifier = modifier,
                    data = ingredients,
                    expanded = ingredientExpanded,
                    onExpandChange = { ingredientExpanded = !ingredientExpanded },
                    onClickBtn = { currentState = RecipeSheetState.FULL_RECIPE },
                )
            }

            RecipeSheetState.FULL_RECIPE -> {
                RecipeFullDetails(
                    nutritionState = nutritionState,
                    instructions = uiState.instructions,
                    summary = uiState.summary,
                    equipments = equipments,
                    expanded = fullRecipeExpanded,
                    onExpandChange = { fullRecipeExpanded = !fullRecipeExpanded },
                    onClickBtn = { currentState = RecipeSheetState.SIMILAR_RECIPES },
                )
            }

            RecipeSheetState.SIMILAR_RECIPES -> {
                SimilarRecipeDetails(
                    uiState = similarRecipesState,
                    expanded = similarRecipeExpanded,
                    onExpandChange = {
                        similarRecipeExpanded = !similarRecipeExpanded
                    },
                )
            }
        }
    }
}

@Composable
fun RecipeSheetBasicDetail(
    modifier: Modifier = Modifier,
    recipe: com.niyaj.core.model.Recipe,
    onClickBtn: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        RecipeBasicDetails(recipe = recipe)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
        ) {
            PrimaryTextWithIconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                text = "Get full recipe",
                icon = RecipeAppIcons.KeyboardArrowRight,
                onClick = onClickBtn,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeIngredientsCard(
    modifier: Modifier = Modifier,
    data: List<com.niyaj.core.model.details.Ingredients>,
    expanded: Boolean,
    onExpandChange: () -> Unit,
    onClickBtn: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        ExpandableCard(
            title = "Ingredients",
            expanded = expanded,
            onExpandChange = onExpandChange,
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .wrapContentHeight(align = Alignment.Top),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            ) {
                data.indices.forEach {
                    RecipeIngredientCard(ingredient = data[it])
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
        ) {
            PrimaryTextWithIconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                text = "Get full recipe",
                icon = RecipeAppIcons.KeyboardArrowRight,
                onClick = onClickBtn,
            )
        }
    }
}

@Composable
fun RecipeFullDetails(
    modifier: Modifier = Modifier,
    nutritionState: NutritionDetailsState,
    instructions: String,
    summary: String,
    equipments: List<com.niyaj.core.model.details.Ingredients>,
    expanded: Boolean,
    onExpandChange: () -> Unit,
    onClickBtn: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        ExpandableCard(
            modifier = Modifier.weight(2.5f, false),
            title = "Full Recipe",
            expanded = expanded,
            onExpandChange = onExpandChange,
        ) {
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                item {
                    RecipeTextCard(
                        title = "Instructions",
                        bodyText = instructions,
                    )
                }

                item {
                    RecipeIngredientsCard(
                        title = "Equipments",
                        data = equipments,
                    )
                }

                item {
                    RecipeTextCard(
                        title = "Quick Summary",
                        bodyText = summary,
                    )
                }

                item {
                    NutritionDetailsCard(
                        nutritionDetailsState = nutritionState,
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .weight(0.3f, true),
        ) {
            PrimaryTextWithIconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                text = "Get Similar recipe",
                icon = RecipeAppIcons.KeyboardArrowRight,
                onClick = onClickBtn,
            )
        }
    }
}

@Composable
fun SimilarRecipeDetails(
    modifier: Modifier = Modifier,
    uiState: SimilarRecipesState,
    expanded: Boolean,
    onExpandChange: () -> Unit,
) {
    ExpandableCard(
        title = "Similar Recipe",
        expanded = expanded,
        onExpandChange = onExpandChange,
    ) {
        Crossfade(
            modifier = modifier,
            targetState = uiState,
            label = "SimilarRecipe::state",
        ) { state ->
            when (state) {
                is SimilarRecipesState.Empty -> {
                    Text(text = "No similar recipe found")
                }

                is SimilarRecipesState.Error -> {
                    Text(text = state.message)
                }

                is SimilarRecipesState.Loading -> CircularProgressIndicator()

                is SimilarRecipesState.Success -> {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(
                            items = state.data,
                            key = { it.id },
                        ) { recipe ->
                            RecipeCard(
                                recipe = recipe,
                                onClickRecipe = {},
                            )
                        }
                    }
                }
            }
        }

    }
}

enum class RecipeSheetState {
    BASIC_DETAILS,
    INGREDIENTS,
    FULL_RECIPE,
    SIMILAR_RECIPES
}