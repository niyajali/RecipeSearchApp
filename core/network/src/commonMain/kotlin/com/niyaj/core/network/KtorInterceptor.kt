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

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.header
import io.ktor.util.AttributeKey

class KtorInterceptor(
    private val getToken: () -> String?,
) {
    companion object Plugin : HttpClientPlugin<Config, KtorInterceptor> {
        override val key: AttributeKey<KtorInterceptor> = AttributeKey("KtorInterceptor")

        override fun install(plugin: KtorInterceptor, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                context.header("Content-Type", "application/json")
                context.header("Accept", "application/json")

                plugin.getToken()?.let { token ->
                    if (token.isNotEmpty()) {
                        context.header("x-api-key", "Bearer $token")
                    }
                }
            }
        }

        override fun prepare(block: Config.() -> Unit): KtorInterceptor {
            val config = Config().apply(block)
            return KtorInterceptor(config.getToken)
        }


    }
}

class Config {
    lateinit var getToken: () -> String?
}