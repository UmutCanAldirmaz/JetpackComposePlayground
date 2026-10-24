package com.example.jetpackcomposeplayground.home

import androidx.annotation.StringRes

data class PracticeApp(
    val id: AppRoute,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
    @param:StringRes val categoryRes: Int
)
