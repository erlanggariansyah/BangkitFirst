package com.erlanggariansyah.bangkitfirst

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erlanggariansyah.bangkitfirst.adapter.ArticleAdapter
import com.erlanggariansyah.bangkitfirst.dto.Article

class MainActivity : AppCompatActivity() {
    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvArticles = findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)

        list.addAll(getListArticles())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListArticles(): ArrayList<Article> {
        val titles = resources.getStringArray(R.array.titles)
        val subtitles = resources.getStringArray(R.array.subtitles)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val images = resources.obtainTypedArray(R.array.images)

        val articles = ArrayList<Article>()
        for (i in titles.indices) {
            val hero = Article(titles[i], subtitles[i], descriptions[i], images.getResourceId(i, -1))
            articles.add(hero)
        }

        return articles
    }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val articleAdapter = ArticleAdapter(list)
        rvArticles.adapter = articleAdapter


        articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                showItemDetail(data)
            }
        })
    }

    private fun showItemDetail(article: Article) {
        val intent: Intent = Intent(this@MainActivity, SecondaryActivity::class.java)
        intent.putExtra("article", article)

        startActivity(intent)
    }
}
