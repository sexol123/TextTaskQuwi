package com.example.testtask.di

import android.app.Application
import com.example.testtask.BuildConfig
import com.example.testtask.data.localstorage.LocalStorageRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT_SEC = 30L

val retrofitModule = module {
    single { provideRetrofit(get(), get()) }
    factory { provideOkhttpClient(get()) }
    factory { provideHttpCache(androidApplication()) }
    factory<Gson> { provideGson() }
}

fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
        .setLenient()
        .serializeNulls()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    return gsonBuilder.create()
}

fun provideHttpCache(application: Application): Cache {
    val cacheSize = 10L * 1024 * 1024
    return Cache(application.cacheDir, cacheSize)
}

fun provideOkhttpClient(cache: Cache): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(cache)
        connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        writeTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)

        addInterceptor { chain ->
            val localStorageRepository: LocalStorageRepository =
                get(LocalStorageRepository::class.java)

            val request: Request = chain.request()

            val change: Request.Builder = request.newBuilder().apply {
                header("Content-Type", "application/json")
                header("Authorization","Bearer ${localStorageRepository.getBearerToken().value}")
            }

            chain.proceed(change.build())
        }

        if (BuildConfig.DEBUG) {
            //Should be last in the chain!!!
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(httpLoggingInterceptor)
        }
    }.build()
}

fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
