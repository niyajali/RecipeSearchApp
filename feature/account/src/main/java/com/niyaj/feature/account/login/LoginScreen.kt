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

package com.niyaj.feature.account.login

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niyaj.core.designsystem.component.PrimaryIconWithTextButton
import com.niyaj.feature.account.R

@Composable
fun LoginScreenRoute(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoginScreen(
        modifier = modifier,
        backgroundImage = R.drawable.login_background,
        onLoginClick = onLoginClick,
    )
}

@Preview
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    @DrawableRes
    backgroundImage: Int = R.drawable.login_background,
    onLoginClick: () -> Unit = {},
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat()/2,  // 1/2
        endY = sizeImage.height.toFloat()
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .align(Alignment.TopStart)
                .onGloballyPositioned {
                    sizeImage = it.size
                },
        )

        Box(modifier = Modifier
            .matchParentSize()
            .background(gradient))

        Surface(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                val annotatedString = AnnotatedString
                    .Builder()
                annotatedString.append("Welcome to ")
                annotatedString
                    .withStyle(SpanStyle(fontWeight = FontWeight.Bold)){append("Rec")}

                annotatedString
                    .withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Normal,
                        )
                    ){
                        append("ii")
                    }
                annotatedString
                    .withStyle(SpanStyle(fontWeight = FontWeight.Bold)){append("p")}
                annotatedString
                    .withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Normal,
                        )
                    ){
                        append("ii")
                    }
                annotatedString
                    .withStyle(SpanStyle(fontWeight = FontWeight.Bold)){append("e")}

                Text(
                    text = annotatedString.toAnnotatedString(),
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 48.sp),
                    fontStyle = FontStyle.Italic,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Please signing to continue",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp),
                )

                Spacer(modifier = Modifier.height(12.dp))

                PrimaryIconWithTextButton(
                    text = "Continue With Google",
                    icon = R.drawable.google_icon,
                    onClick = onLoginClick
                )
            }
        }
    }
}