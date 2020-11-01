package com.lupesoft.pruebadeingreso.data.post


import com.lupesoft.pruebadeingreso.api.Api
import com.lupesoft.pruebadeingreso.api.dto.toDomainModel
import com.lupesoft.pruebadeingreso.api.response.ApiEmptyResponse
import com.lupesoft.pruebadeingreso.api.response.ApiErrorResponse
import com.lupesoft.pruebadeingreso.api.response.ApiResponse
import com.lupesoft.pruebadeingreso.api.response.ApiSuccessResponse
import com.lupesoft.pruebadeingreso.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PostRepository @Inject constructor(private val service: Api) {

    fun getFromNetwork(id: Int): Flow<Resource<List<Post>>> {
        val flow = flow {
            val response = service.getPosts(id)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
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

}