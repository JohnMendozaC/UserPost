package com.lupesoft.pruebadeingreso.data.user

import com.lupesoft.pruebadeingreso.api.Api
import com.lupesoft.pruebadeingreso.api.dto.toDatabaseModel
import com.lupesoft.pruebadeingreso.api.dto.toDomainModel
import com.lupesoft.pruebadeingreso.api.response.ApiEmptyResponse
import com.lupesoft.pruebadeingreso.api.response.ApiErrorResponse
import com.lupesoft.pruebadeingreso.api.response.ApiResponse
import com.lupesoft.pruebadeingreso.api.response.ApiSuccessResponse
import com.lupesoft.pruebadeingreso.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val service: Api
) {

    private fun getFromNetwork(): Flow<Resource<List<UserVo>>> {
        val flow = flow {
            val response = service.getUsers()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    emit(Resource.loading(null, "Saving data in database..."))
                    userDao.insertAll(*apiResponse.body.toDatabaseModel().toTypedArray())
                    emit(Resource.success(apiResponse.body.toDomainModel()))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.error(null, 0, "Empty response"))
                }
                is ApiErrorResponse -> {
                    emit(Resource.error(null, apiResponse.code, apiResponse.message))
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Service fetching...")) }
            .catch { exception ->
                with(exception) {
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }

    fun getFromDatabase(): Flow<Resource<List<UserVo>>> {
        val flow = flow {
            userDao.getUsers().collect {
                if (it.isNotEmpty()) {
                    emit(Resource.success(it.toDomainModel()))
                } else {
                    getFromNetwork().collect { service ->
                        emit(service)
                    }
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Loading from database...")) }
            .catch { exception ->
                with(exception) {
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }
}