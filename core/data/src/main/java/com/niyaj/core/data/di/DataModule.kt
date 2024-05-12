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

package com.niyaj.core.data.di

import com.niyaj.core.data.data.repositoryImpl.RecipeRepositoryImpl
import com.niyaj.core.data.data.repositoryImpl.UserDataRepositoryImpl
import com.niyaj.core.data.repository.RecipeRepository
import com.niyaj.core.data.repository.UserDataRepository
import com.niyaj.core.data.util.ConnectivityManagerNetworkMonitor
import com.niyaj.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun bindsRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl,
    ): RecipeRepository

    @Binds
    internal abstract fun bindsUserDataRepository(
        userRepositoryImpl: UserDataRepositoryImpl,
    ): UserDataRepository
}