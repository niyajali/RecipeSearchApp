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

package com.niyaj.core.network

import com.niyaj.core.network.service.RecipeService
import com.niyaj.core.network.utils.FlowConverterFactory
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient

class KtorfitClient(
    private val ktorfit: Ktorfit,
) {

    val recipeService by lazy { ktorfit.create<RecipeService>() }

    class Builder internal constructor() {
        private lateinit var baseURL: String
        private lateinit var httpClient: HttpClient

        fun baseURL(baseURL: String): Builder {
            this.baseURL = baseURL
            return this
        }

        fun httpClient(ktorHttpClient: HttpClient): Builder {
            this.httpClient = ktorHttpClient
            return this
        }

        fun build(): KtorfitClient {
            val ktorfitBuilder = Ktorfit.Builder()
                .httpClient(httpClient)
                .baseUrl(baseURL)
                .converterFactories(FlowConverterFactory())
                .build()

            return KtorfitClient(ktorfitBuilder)
        }
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }
}