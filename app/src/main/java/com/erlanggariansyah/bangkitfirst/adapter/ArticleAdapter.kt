package com.erlanggariansyah.bangkitfirst.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erlanggariansyah.bangkitfirst.R
import com.erlanggariansyah.bangkitfirst.dto.Article

class ArticleAdapter(private val articles: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var subtitle: TextView = itemView.findViewById(R.id.subtitle)
        var image: ImageView = itemView.findViewById(R.id.image)
        lateinit var description: String
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, subtitle, description, image) = articles[position]
        holder.title.text = title
        holder.subtitle.text = subtitle
        holder.image.setImageResource(image)
        holder.description = description.orEmpty()

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(articles[holder.adapterPosition]) }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}
