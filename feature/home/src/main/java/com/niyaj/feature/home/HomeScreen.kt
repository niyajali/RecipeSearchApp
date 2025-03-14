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

package com.niyaj.feature.home

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.niyaj.core.designsystem.component.StandardHorizontalPager
import com.niyaj.core.designsystem.component.StandardSearchBar
import com.niyaj.core.model.Recipe
import com.niyaj.core.ui.LoadingIndicator
import com.niyaj.core.ui.R
import com.niyaj.core.ui.RecipeCard
import com.niyaj.core.ui.StandardErrorMessage

@Composable
fun HomeScreenRoute(
    onClickSearch: () -> Unit,
    onClickRecipe: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val recipeListState by viewModel.randomRecipeList.collectAsStateWithLifecycle()
    val popularRecipesState by viewModel.popularRecipes.collectAsStateWithLifecycle()

    HomeScreenContent(
        recipeListState = recipeListState,
        popularRecipes = popularRecipesState,
        onClickSearch = onClickSearch,
        onClickRecipe = onClickRecipe,
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    recipeListState: HomeUiState,
    popularRecipes: HomeUiState,
    onClickRecipe: (Int) -> Unit,
    onClickSearch: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 36.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                UserCard(
                    username = "Niyaj Ali",
                    onSearchClick = onClickSearch,
                )
            }

            popularRecipesCard(
                uiState = popularRecipes,
                onClickRecipe = onClickRecipe,
            )

            allRecipesCard(
                uiState = recipeListState,
                onClickRecipe = onClickRecipe,
            )
        }
    }
}

fun LazyListScope.allRecipesCard(
    uiState: HomeUiState,
    onClickRecipe: (Int) -> Unit,
) {
    item {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "All Recipes",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }


    when (uiState) {
        is HomeUiState.Loading -> {
            item {
                LoadingIndicator(modifier = Modifier)
            }
        }

        is HomeUiState.Error -> {
            item {
                StandardErrorMessage(message = uiState.message.toString())
            }
        }

        is HomeUiState.Success -> {
            itemsIndexed(
                items = uiState.data,
                key = { _, item ->
                    item.id
                },
            ) { _, item ->
                RecipeCard(
                    recipe = item,
                    onClickRecipe = onClickRecipe,
                )
            }
        }

    }

}

fun LazyListScope.popularRecipesCard(
    uiState: HomeUiState,
    onClickRecipe: (Int) -> Unit,
) {
    item {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Popular Recipes",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Crossfade(
            targetState = uiState,
            label = "PopularRecipe::State",
        ) { state ->
            when (state) {
                is HomeUiState.Loading -> Unit

                is HomeUiState.Error -> {
                    StandardErrorMessage(message = state.message.toString())
                }

                is HomeUiState.Success -> {
                    val pagerState = rememberPagerState { state.data.size }

                    StandardHorizontalPager(
                        pagerState = pagerState,
                    ) {
                        PopularRecipeCard(
                            recipe = state.data[it],
                            onClickRecipe = onClickRecipe,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    username: String,
    onSearchClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = "\uD83D\uDC4B Hey $username",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "Discover tasty and healthy receipt",
                style = MaterialTheme.typography.bodyMedium,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        onSearchClick()
                    },
            ) {
                StandardSearchBar(enabled = false)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PopularRecipeCard(
    modifier: Modifier = Modifier,
    recipe: com.niyaj.core.model.Recipe,
    onClickRecipe: (Int) -> Unit,
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat() / 2,  // 1/2
        endY = sizeImage.height.toFloat(),
    )

    BoxWithConstraints(
        modifier = modifier
            .size(156.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClickRecipe(recipe.id) },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.image)
                .placeholder(R.drawable.core_ui_image_placeholder)
                .error(R.drawable.core_ui_image_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = recipe.imageType,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .onGloballyPositioned {
                    sizeImage = it.size
                },
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(gradient),
        )

        Surface(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "Ready in ${recipe.readyInMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

