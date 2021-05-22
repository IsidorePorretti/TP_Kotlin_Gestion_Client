package fr.epf.min1.gcIsidore

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("/api")
    suspend fun getClients(@Query("results") results : Int = 20) : GetClientsResult
}

data class GetClientsResult(val results: List<User>)

data class User(val gender: String, val email: String, val name: Name)

data class Name(val first: String, val last: String)
