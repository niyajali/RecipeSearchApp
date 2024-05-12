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

package com.niyaj.core.network.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductMatchesResponse(
    @Json(name = "averageRating")
    val averageRating: Double = 0.0,

    @Json(name = "description")
    val description: String = "",

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "imageUrl")
    val imageUrl: String = "",

    @Json(name = "link")
    val link: String = "",

    @Json(name = "price")
    val price: String = "",

    @Json(name = "ratingCount")
    val ratingCount: Double = 0.0,

    @Json(name = "score")
    val score: Double = 0.0,

    @Json(name = "title")
    val title: String = ""
)