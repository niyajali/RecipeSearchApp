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

package com.niyaj.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.niyaj.core.designsystem.theme.Gray1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardSearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onTrailingClick: () -> Unit = {},
    onLeadingClick: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    active: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    leadingIcon: ImageVector = Icons.Outlined.Search,
    trailingIcon: ImageVector = Icons.Outlined.Close,
    placeholder: String = "Search any recipe",
    focusRequester: FocusRequester = remember { FocusRequester() },
    content: @Composable ColumnScope.() -> Unit = {}
) {
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        query = searchQuery,
        onQueryChange = onSearchQueryChanged,
        onSearch = onSearch,
        active = active,
        enabled = enabled,
        onActiveChange = onActiveChange,
        shape = shape,
        leadingIcon = {
            IconButton(onClick = onLeadingClick) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "LeadingIcon",
                )
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotEmpty()
            ) {
                IconButton(
                    onClick = onTrailingClick
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Clear Query"
                    )
                }
            }
        },
        placeholder = {
            Text(text = placeholder)
        },
        content = content,
        colors = SearchBarDefaults.colors(
            containerColor = Gray1
        ),
    )
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onTrailingClick: () -> Unit = {},
    onLeadingClick: () -> Unit = {},
    leadingIcon: ImageVector = Icons.Outlined.Search,
    trailingIcon: ImageVector = Icons.Outlined.Close,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Gray1,
            unfocusedContainerColor = Gray1
        ),
        leadingIcon = {
            IconButton(onClick = onLeadingClick) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "LeadingIcon",
                )
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotEmpty()
            ) {
                IconButton(
                    onClick = onTrailingClick
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Clear Query"
                    )
                }
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onKeyEvent {
                it.key == Key.Enter
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(12.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        maxLines = 1,
        singleLine = true,
    )
}