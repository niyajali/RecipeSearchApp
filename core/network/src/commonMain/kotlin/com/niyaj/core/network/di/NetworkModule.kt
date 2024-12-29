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

package com.niyaj.core.network.di

import com.niyaj.core.network.KtorInterceptor
import com.niyaj.core.network.KtorfitClient
import com.niyaj.core.network.ktorHttpClient
import com.niyaj.core.network.utils.BaseURL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import org.koin.dsl.module

val NetworkModule = module {
    single<HttpClient>(KtorClient) {
        ktorHttpClient.config {
            install(Auth)
            install(KtorInterceptor) {
                getToken = { BaseURL.API_KEY }
            }
        }
    }

    single<KtorfitClient>(BaseClient) {
        KtorfitClient.builder()
            .httpClient(get(KtorClient))
            .baseURL(BaseURL.url)
            .build()
    }
}