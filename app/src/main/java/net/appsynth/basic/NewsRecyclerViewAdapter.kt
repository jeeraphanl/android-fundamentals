package net.appsynth.basic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsRecyclerViewAdapter : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    var articleList = mutableListOf<FeedResponse.Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size.let { it }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.apply {
            val article = articleList[position]
            titleTextView.text = article.title
            descTextView.text = article.description

            Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.place_holder)
                    .into(coverImageView)
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}