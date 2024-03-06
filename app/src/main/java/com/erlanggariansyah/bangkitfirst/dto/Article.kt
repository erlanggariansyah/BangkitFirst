package com.erlanggariansyah.bangkitfirst.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String?,
    val subTitle: String?,
    val description: String?,
    val image: Int
) : Parcelable
