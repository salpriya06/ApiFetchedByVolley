package com.example.itbook.model

import java.io.Serializable

data class Book(
    val title: String = "",
    val subtitle: String = "",
    val isbn13: String = "",
    val price: String = "",
    val image: String = "",
    val url: String = ""
): Serializable