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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niyaj.core.designsystem.icon.RecipeAppIcons
import com.niyaj.core.designsystem.theme.Gray1
import com.niyaj.core.designsystem.theme.Gray10
import com.niyaj.core.designsystem.theme.Gray6
import com.niyaj.feature.recipe_details.NutritionDetailsState

@Composable
fun NutritionDetailsCard(
    modifier: Modifier = Modifier,
    nutritionDetailsState: NutritionDetailsState,
) {
    var nutritionExpanded by remember { mutableStateOf(true) }
    var goodForExpanded by remember { mutableStateOf(false) }
    var badForExpanded by remember { mutableStateOf(false) }

    Crossfade(
        modifier = modifier,
        targetState = nutritionDetailsState,
        label = "NutritionDetailsState",
    ) { state ->
        when (state) {
            is NutritionDetailsState.Loading -> Unit

            is NutritionDetailsState.Error -> Unit

            is NutritionDetailsState.Success -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val nutritionText = state.data.nutrients.joinToString(
                        separator = ", ",
                    ) {
                        "${it.name}(${it.percentOfDailyNeeds} ${it.unit})"
                    }

                    val badHealthText = state.data.bad.joinToString(
                        separator = ", ",
                    ) {
                        "${it.title}(${it.percentOfDailyNeeds})"
                    }

                    val goodHealthText = state.data.good.joinToString(
                        separator = ", ",
                    ) {
                        "${it.title}(${it.percentOfDailyNeeds})"
                    }

                    RecipeExpandableCard(
                        title = "Nutrients",
                        bodyText = nutritionText,
                        isExpanded = nutritionExpanded,
                        onExpandChange = {
                            nutritionExpanded = !nutritionExpanded
                        },
                    )

                    RecipeExpandableCard(
                        title = "Bad for health Nutrition",
                        bodyText = badHealthText,
                        isExpanded = badForExpanded,
                        onExpandChange = {
                            badForExpanded = !badForExpanded
                        },
                    )

                    RecipeExpandableCard(
                        title = "Good for health Nutrition",
                        bodyText = goodHealthText,
                        isExpanded = goodForExpanded,
                        onExpandChange = {
                            goodForExpanded = !goodForExpanded
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    bodyText: String,
    isExpanded: Boolean,
    onExpandChange: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val iconModifier = if (isExpanded) Modifier.rotate(180f) else Modifier

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray1)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onExpandChange()
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Gray10,
                )

                Icon(
                    imageVector = RecipeAppIcons.ArrowDropDownCircle,
                    contentDescription = "IsExpanded",
                    modifier = iconModifier,
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = bodyText,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 20.sp,
                    fontSize = 14.sp,
                    color = Gray6,
                )
            }
        }
    }
}