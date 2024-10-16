package com.example.topicsgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    val code: Int,
    @DrawableRes val iconRes: Int
)
