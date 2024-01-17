package com.example.itbook.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.itbook.R
import com.example.itbook.model.Book
import com.squareup.picasso.Picasso

class BookListAdapter(val bookList: ArrayList<Book>, val context: Context) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {


    interface SetOnClickListener{
        fun setOnClick(position: Int)
    }

    var setOnClickListener: SetOnClickListener? = null

    fun setOnClickListener(listener: SetOnClickListener) {
        setOnClickListener = listener
    }
    
    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var imgBook: ImageView
        lateinit var txtTitle: TextView

        init {
            imgBook = view.findViewById<ImageView>(R.id.imgBook)
            txtTitle = view.findViewById<TextView>(R.id.txtTitle)

            view.setOnClickListener {
                setOnClickListener?.setOnClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_book_layout, parent,false)
        return BookViewHolder(view)

    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val data = bookList[position]

        holder.txtTitle.text = data.title

        Picasso.get()
            .load(data.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.imgBook)



    }

}