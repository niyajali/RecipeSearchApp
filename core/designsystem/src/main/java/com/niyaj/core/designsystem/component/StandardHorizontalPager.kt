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

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StandardHorizontalPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pageSize: PageSize = PageSize.Fixed(156.dp),
    pageSpacing: Dp = 11.dp,
    pageContent: @Composable() (PagerScope.(page: Int) -> Unit),
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = pageSize,
        pageSpacing = pageSpacing,
        pageContent = pageContent
    )
}