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

package com.niyaj.feature.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.niyaj.core.designsystem.component.StandardSearchBar
import com.niyaj.core.designsystem.icon.RecipeAppIcons
import com.niyaj.core.model.SearchResult
import com.niyaj.core.ui.LoadingIndicator
import com.niyaj.core.ui.R
import com.niyaj.core.ui.StandardErrorMessage

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSearchItemClick: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchResultState by viewModel.searchResult.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchScreen(
        modifier = modifier,
        uiState = searchResultState,
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onClearClick = viewModel::onClearClick,
        onSearchItemClick = {
            keyboardController?.hide()
            onSearchItemClick(it)
        },
        onBackClick = onBackClick,
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUIState,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchItemClick: (Int) -> Unit,
    onClearClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        StandardSearchBar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            leadingIcon = RecipeAppIcons.ArrowBack,
            onTrailingClick = onClearClick,
            onLeadingClick = onBackClick,
            focusRequester = focusRequester,
        )

        Crossfade(
            targetState = uiState,
            label = "SearchUi::State",
        ) { state ->
            when (state) {
                is SearchUIState.Loading -> LoadingIndicator()

                is SearchUIState.Empty -> {
                    StandardErrorMessage(message = "No result founds")
                }

                is SearchUIState.Error -> {
                    StandardErrorMessage(message = state.message)
                }

                is SearchUIState.Success -> {
                    SearchResultBody(
                        searchQuery = searchQuery,
                        results = state.data,
                        onSearchItemClick = onSearchItemClick,
                    )
                }
            }
        }

        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

// TODO: Bold matches search text based on search query
@Composable
fun SearchResultBody(
    modifier: Modifier = Modifier,
    searchQuery: String,
    results: List<com.niyaj.core.model.SearchResult>,
    onSearchItemClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            items = results,
            key = { item -> item.id },
        ) { item ->
            SearchResultBodyItem(
                item = item,
                onSearchItemClick = onSearchItemClick,
            )
        }
    }
}

@Composable
fun SearchResultBodyItem(
    modifier: Modifier = Modifier,
    item: com.niyaj.core.model.SearchResult,
    onSearchItemClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onSearchItemClick(item.id) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.core_ui_item_icon),
                contentDescription = "Search Item Icon",
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}