package net.kathir.koinDI.di.module

import android.content.Context
import net.kathir.koinDI.data.api.ApiHelper
import net.kathir.koinDI.data.api.ApiHelperImpl
import net.kathir.koinDI.data.api.ApiService
import net.kathir.koinDI.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), "https://5e510330f2c0d300147c034c.mockapi.io/") }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single <ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if(BuildConfig.DEBUG)
{
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
}
else OkHttpClient.Builder().build()


private fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String) :
        Retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create()).baseUrl(BASE_URL).client(okHttpClient).build()

private fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

