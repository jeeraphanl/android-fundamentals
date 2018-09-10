package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    private fun getNews() {

        val newService = NewsService.instance
        val newsCall = newService.getNews()

        newsCall.enqueue(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
            }
        })
    }
}
