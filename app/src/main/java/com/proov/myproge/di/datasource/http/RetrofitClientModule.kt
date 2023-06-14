package com.proov.myproge.di.datasource.http
// Siin tuuakse sisse teie projekti teised klassid ja teegid.
import com.proov.myproge.data.api.DateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Annotatsioon @Module tähistab, et see klass sisaldab sõltuvuste pakkumise funktsioone.
@Module
// Annotatsioon @InstallIn määrab, kus neid sõltuvusi saab kasutada. SingletonComponent tähendab,
// et neid sõltuvusi saab kasutada kogu teie rakenduses.
@InstallIn(SingletonComponent::class)
// object deklareerib singltoni, millest saab luua ainult ühe eksemplari. See on Dagger ja Hilt raamistikele
// omane, et sõltuvuste süstimise moodulid on tihtipeale singletonid.
object RetrofitClientModule {

    // @Provides annotatsiooniga tähistatud funktsioonid on need, mis pakuvad sõltuvusi. See funktsioon pakub Retrofiti klienti.
    // @Singleton tähistab, et seda sõltuvust hoitakse mälus niikaua kui rakendus on käimas, mitte ei tehta seda iga kord uuesti.
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Siin luuakse uus Retrofiti klient.
        return Retrofit.Builder()
            // Baas-URL määrab, millist serveri aadressi API kutsete puhul kasutatakse.
            .baseUrl("http://10.0.2.2:3000/") // asendage see oma API baas-URL-iga
            // GsonConverterFactory aitab Retrofitil teisendada JSON-i Java või Kotlini objektideks.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Sarnaselt eelmise funktsiooniga, @Provides tähistab selle funktsiooni kui sõltuvuse pakkujat.
    // See funktsioon kasutab eelmise funktsiooni poolt pakutud Retrofiti klienti, et luua DateApi liidese eksemplar.
    @Provides
    @Singleton
    fun provideDateApi(retrofit: Retrofit): DateApi {
        // retrofit.create() funktsioon loob uue API-liidese eksemplari.
        return retrofit.create(DateApi::class.java)
    }
}