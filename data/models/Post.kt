package com.sample.app.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Post(
    val id: Long,
    val title: String,
    val body: String
) : Parcelable