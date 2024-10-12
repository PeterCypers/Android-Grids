package com.example.gridwithimages.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val stringResourceId: Int,
    val amount: Int,
    @DrawableRes val imageResourceId: Int
)