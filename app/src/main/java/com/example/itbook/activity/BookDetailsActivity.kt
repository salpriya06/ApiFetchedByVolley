package com.example.itbook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itbook.R
import com.example.itbook.databinding.ActivityBookDetailsBinding
import com.example.itbook.model.Book
import com.squareup.picasso.Picasso

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        extrackBookData()

    }

    private fun extrackBookData() {

        val data = intent

        val book = data.getSerializableExtra("bookInfo") as Book

        binding.txtBookTitle.text = "Title - ${book.title}"

        binding.txtBookSubTitle.text = "Subtitle - ${book.subtitle}"

        binding.txtBookIsbn.text = "isbn13 - ${book.isbn13}"

        binding.txtBookPrice.text = "Price - ${book.price}"

        binding.txtBookUrl.text = book.url

        Picasso.get()
            .load(book.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(binding.imgBook)

    }

    override fun onBackPressed() {
        finish()
    }

}