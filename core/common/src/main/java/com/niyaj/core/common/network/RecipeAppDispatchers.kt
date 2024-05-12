package com.niyaj.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: RecipeAppDispatchers)

enum class RecipeAppDispatchers {
    Default,
    IO,
}
