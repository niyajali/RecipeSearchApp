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
data class ExtendedIngredientResponse(

    @Json(name = "aisle")
    val aisle: String? = null,

    @Json(name = "amount")
    val amount: Double? = null,

    @Json(name = "consistency")
    val consistency: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "measures")
    val measures: MeasuresResponse? = null,

    @Json(name = "meta")
    val meta: List<String>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "nameClean")
    val nameClean: String? = null,

    @Json(name = "original")
    val original: String? = null,

    @Json(name = "originalName")
    val originalName: String? = null,

    @Json(name = "unit")
    val unit: String? = null,
)