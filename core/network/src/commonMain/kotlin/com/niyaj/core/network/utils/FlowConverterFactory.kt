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

package com.niyaj.core.network.utils

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Factory that enables the use of Flow<T> as return type
 */
public class FlowConverterFactory : Converter.Factory {
    override fun responseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit,
    ): Converter.ResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == Flow::class) {
            return object : Converter.ResponseConverter<HttpResponse, Flow<Any?>> {
                override fun convert(getResponse: suspend () -> HttpResponse): Flow<Any?> {
                    val requestType = typeData.typeArgs.first()
                    return flow {
                        val response = getResponse()
                        if (requestType.typeInfo.type == HttpResponse::class) {
                            emit(response)
                        } else {
                            val convertedBody =
                                ktorfit.nextSuspendResponseConverter(
                                    this@FlowConverterFactory,
                                    typeData.typeArgs.first(),
                                )?.convert(KtorfitResult.Success(response))
                                    ?: response.body(typeData.typeArgs.first().typeInfo)
                            emit(convertedBody)
                        }
                    }
                }
            }
        }
        return null
    }
}