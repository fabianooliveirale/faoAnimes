package com.fabiano.faoanime.api

import com.fabiano.faoanime.BuildConfig
import com.fabiano.faoanime.models.responses.SearchReponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {
    @GET("search/anime")
    fun search(
        @Query("q") seachString: String,
        @Query("page") page: Int
    ): Observable<SearchReponse>

    companion object Factory {
        fun public(): NetworkApi {
            val interceptor = Interceptor { chain ->
                try {
                    return@Interceptor chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build()
                    )
                } catch (x: Throwable) {
                    throw x
                }
            }

            val httpCliente = HttpClientBuilderFactory.create(interceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URLAPI)
                .client(httpCliente)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NetworkApi::class.java)
        }
    }
}