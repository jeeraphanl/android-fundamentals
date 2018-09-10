package net.appsynth.basic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerViewAdapter = NewsRecyclerViewAdapter()
        newsRecyclerView.adapter = newsRecyclerViewAdapter

        getNews()
    }

    private fun getNews() {

        val newService = NewsService.instance
        val newsCall = newService.getNews()

        newsCall.enqueue(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
                response?.body()?.articles?.let { articleList ->
                    newsRecyclerViewAdapter.articleList = articleList
                    newsRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}
