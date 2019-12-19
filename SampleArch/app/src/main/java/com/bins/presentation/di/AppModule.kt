package com.bins.presentation.di

import androidx.room.Room
import com.bins.BuildConfig
import com.bins.data.api.RandomUserApi
import com.bins.data.db.UserDatabase
import com.bins.data.mappers.DbEntityToDataEntityMapper
import com.bins.data.mappers.EntityToDataMapper
import com.bins.data.repository.UserCacheImpl
import com.bins.data.repository.UserRemoteRepoImpl
import com.bins.data.repository.UserRepositoryImpl
import com.bins.domain.repo.UserRepo
import com.bins.domain.usecases.UserInfoUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { UserRemoteRepoImpl(api = get(API))}
    single(name = "local") {
        UserCacheImpl(database = get(DATABASE), entityToDataMapper = EntityToDataMapper(),
            dbToDataMapper = DbEntityToDataEntityMapper()
        )
    }
    single { UserRepositoryImpl(remote = get("remote"), cache = get("local")) as UserRepo }
}

val mUseCaseModules = module {
    factory(name = GET_NEWS_USECASE) { UserInfoUseCase(coroutineContext = Dispatchers.Default, repositories = get()) }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BuildConfig.BASEURL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RandomUserApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) { Room.databaseBuilder(androidApplication(), UserDatabase::class.java, "RandomUserSample").build() }
}


private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getRandomUserUseCase"
private const val DATABASE = "database"