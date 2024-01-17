package com.example.itbook.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itbook.databinding.ActivityMainBinding
import com.example.itbook.model.Book
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.itbook.adapters.BookListAdapter
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var bookList = ArrayList<Book>()

    private lateinit var requestQueue: RequestQueue

    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRequestQueue()
        setUpRecyclerView()
        initData()


    }

    private fun initRequestQueue() {
        requestQueue = Volley.newRequestQueue(this@MainActivity)
    }

    private fun initData() {

        val req = JsonObjectRequest(
            Request.Method.GET,
            "https://api.itbook.store/1.0/new",
            null,
            { jsonObject ->

                Log.e("json", jsonObject.toString())

               val error = jsonObject.get("error")

                val gson = Gson()

                val usersArray = gson.fromJson<Array<Book>>(
                    jsonObject.getJSONArray("books").toString(),
                    Array<Book>::class.java)
                bookList.addAll(usersArray)

                bookListAdapter = BookListAdapter(bookList,this@MainActivity)

                binding.rvBookList.adapter = bookListAdapter

                bookListAdapter.setOnClickListener = SetOnClickListener()

            },
            { error ->
                Log.e("tag", "error")
            }
        )
        requestQueue.add(req)


    }

    private fun setUpRecyclerView(){
        binding.rvBookList.layoutManager = GridLayoutManager(this@MainActivity,2)
    }


    inner class SetOnClickListener : BookListAdapter.SetOnClickListener{
        override fun setOnClick(position: Int) {
            val intent = Intent(this@MainActivity,BookDetailsActivity::class.java)
            intent.putExtra("bookInfo",bookList[position])
            startActivity(intent)
        }
    }

}