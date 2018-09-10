package net.appsynth.basic

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://newsapi.org/v2/"

interface NewsService {

    companion object {
        val instance: NewsService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create<NewsService>(NewsService::class.java)
        }
    }

    @GET("top-headlines?sources=abc-news&apiKey=41d2725710ae481795ff89337da615fe")
    fun getNews(): Call<FeedResponse>
}