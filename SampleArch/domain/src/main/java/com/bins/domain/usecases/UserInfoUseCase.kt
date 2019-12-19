package com.bins.domain.usecases

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.UserResponseEntity
import com.bins.domain.repo.UserRepo
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class UserInfoUseCase(private val coroutineContext: CoroutineContext,
                      private val repositories: UserRepo) : BaseUseCase<UserResponseEntity>(coroutineContext) {

    override suspend fun getUserData(data: Map<String, Any>?): ReceiveChannel<DataEntity<UserResponseEntity>> {
        return repositories.getUsers()
    }

    override suspend fun sendToPresentation(data: DataEntity<UserResponseEntity>): DataEntity<UserResponseEntity> {
        return data
    }


}