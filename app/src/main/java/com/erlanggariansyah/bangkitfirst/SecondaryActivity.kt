package com.erlanggariansyah.bangkitfirst

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.erlanggariansyah.bangkitfirst.dto.Article

class SecondaryActivity : AppCompatActivity() {
    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var imageView: ImageView
    private lateinit var aboutButton: Button
    private lateinit var shareButton: Button

    private lateinit var articleObjectGlobal: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        titleText = findViewById(R.id.title)
        subtitleText = findViewById(R.id.subtitle)
        descriptionText = findViewById(R.id.description)
        imageView = findViewById(R.id.image)

        val article = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("article", Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("article")
        }

        if (article != null) {
            titleText.text = article.title
            subtitleText.text = article.subTitle
            descriptionText.text = article.description
            imageView.setImageResource(article.image)

            articleObjectGlobal = article
        }

        aboutButton = findViewById(R.id.about)
        shareButton = findViewById(R.id.share)
    }

    override fun onResume() {
        super.onResume()

        aboutButton.setOnClickListener {
            val intent = Intent(this@SecondaryActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_SUBJECT, articleObjectGlobal.title);
            intent.putExtra(Intent.EXTRA_TEXT, articleObjectGlobal.description);

            startActivity(Intent.createChooser(intent, "Share with"))
        }
    }
}
