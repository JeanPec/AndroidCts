package com.shindra.ctslibrary.webservice

import StopPoints
import com.shindra.arrakis.webservice.RetrofitServices
import com.shindra.ctslibrary.bo.EstimatedTimeTable
import com.shindra.ctslibrary.bo.Lines
import com.shindra.ctslibrary.bo.RouteType
import com.shindra.ctslibrary.bo.VeloParcs
import io.reactivex.rxjava3.core.Single
import okhttp3.*
import retrofit2.http.GET
import retrofit2.http.Query

private const val CTS_API_TOKEN = "a9308ac0-cc13-4519-bca2-f0c21166b197"

interface CtsApiService {

    @GET("v1/cts/veloparc")
    fun veloParc(): Single<VeloParcs>

    @GET("/v1/siri/2.0/stoppoints-discovery")
    fun stopPoints(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("distance") distance: Int,
        @Query("includeLinesDestinations") includeLinesDestinations: Boolean = true,
        @Query("stopCode") stopCode: String?
    ): Single<StopPoints>

    @GET("v1/siri/2.0/lines-discovery")
    fun linesDiscovery(): Single<Lines>

    @GET("/v1/siri/2.0/estimated-timetable")
    fun estimatedTimeTable(@Query("VehicleMode") routeType: RouteType,
                           @Query("LineRef") lineRef: String,
                           @Query("DirectionRef") directionRef: Int) : Single<EstimatedTimeTable>

}

object CtsService : RetrofitServices<CtsApiService>() {

    override fun apiServiceClass() = CtsApiService::class.java

    override fun getUrl() = "https://api.cts-strasbourg.eu"

    override fun okHttpBuilder(): OkHttpClient.Builder {
        return super.okHttpBuilder().authenticator(BasicAuthenticator(CTS_API_TOKEN, ""))
    }

    fun veloParc(): Single<VeloParcs> {
        return service.veloParc()
    }

    fun stopPoints(latitude: Double,
                   longitude: Double, distance:
                   Int, includeLinesDestinations: Boolean = true,
                   stopCode: String? = null): Single<StopPoints> {
        return service.stopPoints(latitude, longitude, distance, includeLinesDestinations, stopCode)
    }

    fun linesDiscovery(): Single<Lines> {
        return service.linesDiscovery()
    }

    fun estimatedTimeTable(routeType: RouteType,
                           lineRef: String,
                           directionRef: Int) : Single<EstimatedTimeTable> {
        return service.estimatedTimeTable(routeType, lineRef, directionRef)
    }


}

class BasicAuthenticator(private val userName: String, private val userPassword: String) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val credential = Credentials.basic(userName, userPassword)
        return response.request.newBuilder().header("Authorization", credential).build()
    }

}