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
data class TasteResponse(
    @Json(name = "bitterness")
    val bitterness: Double = 0.0,

    @Json(name = "fattiness")
    val fattiness: Double = 0.0,

    @Json(name = "saltiness")
    val saltiness: Double = 0.0,

    @Json(name = "savoriness")
    val savoriness: Double = 0.0,

    @Json(name = "sourness")
    val sourness: Double = 0.0,

    @Json(name = "spiciness")
    val spiciness: Double = 0.0,
    
    @Json(name = "sweetness")
    val sweetness: Double = 0.0
)