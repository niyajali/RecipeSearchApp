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

package com.niyaj.feature.favourite

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niyaj.core.ui.LoadingIndicator
import com.niyaj.core.ui.RecipeCard
import com.niyaj.core.ui.StandardErrorMessage

@Composable
fun FavouriteScreenRoute(
    onClickRecipe: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavouriteScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.favouriteList.collectAsStateWithLifecycle()

    FavouriteScreen(
        modifier = modifier,
        uiState = uiState,
        onClickRecipe = onClickRecipe,
    )
}

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    uiState: FavouriteUiState,
    onClickRecipe: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Crossfade(
            targetState = uiState,
            label = "Favourite::State",
        ) { state ->
            when (state) {
                is FavouriteUiState.Loading -> LoadingIndicator()

                is FavouriteUiState.Empty -> {
                    StandardErrorMessage(message = "No Favourite Recipes")
                }

                is FavouriteUiState.Error -> StandardErrorMessage(message = state.message)

                is FavouriteUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp)
                            .statusBarsPadding(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        item {
                            Text(
                                text = "Favourite recipes",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        items(
                            items = state.data,
                            key = {
                                it.id
                            },
                        ) {
                            RecipeCard(
                                recipe = it,
                                onClickRecipe = onClickRecipe,
                            )
                        }
                    }
                }
            }
        }
    }
}