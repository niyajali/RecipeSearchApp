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

package com.niyaj.feature.recipe_details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.niyaj.core.designsystem.component.StandardHorizontalPager
import com.niyaj.core.designsystem.theme.Gray2
import com.niyaj.core.model.details.Ingredients
import com.niyaj.core.ui.R

@Composable
fun RecipeIngredientsCard(
    modifier: Modifier = Modifier,
    title: String,
    data: List<com.niyaj.core.model.details.Ingredients>,
) {
    val pagerState = rememberPagerState { data.size }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )

        StandardHorizontalPager(
            pagerState = pagerState,
            pageSize = PageSize.Fixed(108.dp),
        ) {
            RecipeIngredientCard(
                ingredient = data[it],
            )
        }
    }
}


@Composable
fun RecipeIngredientCard(
    modifier: Modifier = Modifier,
    ingredient: com.niyaj.core.model.details.Ingredients,
) {
    Box(
        modifier = modifier
            .size(108.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ingredient.image)
                    .placeholder(R.drawable.core_ui_image_placeholder)
                    .crossfade(true)
                    .build(),
                contentDescription = ingredient.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(1.dp, Gray2), CircleShape),
            )

            ingredient.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}