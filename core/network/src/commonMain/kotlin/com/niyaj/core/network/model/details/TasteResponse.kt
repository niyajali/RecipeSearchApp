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


import kotlinx.serialization.Serializable

@Serializable
data class TasteResponse(
    val bitterness: Double = 0.0,

    val fattiness: Double = 0.0,

    val saltiness: Double = 0.0,

    val savoriness: Double = 0.0,

    val sourness: Double = 0.0,

    val spiciness: Double = 0.0,

    val sweetness: Double = 0.0,
)