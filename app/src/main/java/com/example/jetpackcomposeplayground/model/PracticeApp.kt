package com.example.jetpackcomposeplayground.model

import com.example.jetpackcomposeplayground.R

enum class AppRoute {
    HOME,
    DICE_ROLLER,
    LEMONADE,
    TIP_CALCULATOR
}

data class PracticeApp(
    val id: AppRoute,
    val titleRes: Int,
    val descriptionRes: Int,
    val categoryRes: Int
)

val practiceAppList = listOf(
    PracticeApp(
        id = AppRoute.DICE_ROLLER,
        titleRes = R.string.dice_roller_title,
        descriptionRes = R.string.dice_roller_desc,
        categoryRes = R.string.category_basit_ui
    ),
    PracticeApp(
        id = AppRoute.LEMONADE,
        titleRes = R.string.lemonade_title,
        descriptionRes = R.string.lemonade_desc,
        categoryRes = R.string.category_state_machine
    ),
    PracticeApp(
        id = AppRoute.TIP_CALCULATOR,
        titleRes = R.string.tip_calculator_title,
        descriptionRes = R.string.tip_calc_desc,
        categoryRes = R.string.category_forms
    )
)
