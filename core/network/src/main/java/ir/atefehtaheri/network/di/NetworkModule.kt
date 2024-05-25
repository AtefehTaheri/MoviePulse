package ir.atefehtaheri.network.di



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.network.Constants
import ir.atefehtaheri.network.adapter.NetworkResponseCallAdapterFactory
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton

    fun provideRetrofitMovie(
        networkResponseCallAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return provideRetrofit(
            Constants.BASE_URL,
            networkResponseCallAdapterFactory
        )
    }

    @Singleton
    @Provides
    fun provideNetworkResponseCallAdapterFactory(): CallAdapter.Factory {
        return NetworkResponseCallAdapterFactory.create()
    }

//
//    @Provides
//    fun provideContext(@ApplicationContext context: Context): Context {
//        return context
//    }

}
fun provideRetrofit(
    baseUrl: String,
    networkResponseCallAdapterFactory: CallAdapter.Factory
): Retrofit {

    return Retrofit.Builder()

        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(networkResponseCallAdapterFactory)
        .baseUrl(baseUrl)
        .build()
}
inline fun <reified T> createApiService(service: Class<T>, retrofit: Retrofit): T =
    retrofit.create(service)