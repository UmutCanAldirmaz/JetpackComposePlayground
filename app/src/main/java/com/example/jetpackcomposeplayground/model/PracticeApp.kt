package com.example.jetpackcomposeplayground.model

import com.example.jetpackcomposeplayground.R

enum class AppRoute {
    HOME,
    DICE_ROLLER,
    LEMONADE,
    TIP_CALCULATOR,
    ART_SPACE,
    AFFIRMATIONS,
    WOOF,
    SUPER_HEROES,
    EXERCISE,
    COUNTER_EXERCISE,
    DESSERT_CLICKER,
    LAZY_GRID_PRACTICE
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
    ),
    PracticeApp(
        id = AppRoute.ART_SPACE,
        titleRes = R.string.art_space_title,
        descriptionRes = R.string.art_space_desc,
        categoryRes = R.string.category_layout
    ),
    PracticeApp(
        id = AppRoute.AFFIRMATIONS,
        titleRes = R.string.affirmations_title,
        descriptionRes = R.string.affirmations_desc,
        categoryRes = R.string.category_layout
    ),
    PracticeApp(
        id = AppRoute.WOOF,
        titleRes = R.string.woof_title,
        descriptionRes = R.string.woof_desc,
        categoryRes = R.string.category_layout
    ),
    PracticeApp(
        id = AppRoute.SUPER_HEROES,
        titleRes = R.string.superheroes_title,
        descriptionRes = R.string.superheroes_desc,
        categoryRes = R.string.category_layout
    ),
    PracticeApp(
        id = AppRoute.EXERCISE,
        titleRes = R.string.exercise_title,
        descriptionRes = R.string.exercise_desc,
        categoryRes = R.string.category_sandbox
    ),
    PracticeApp(
        id = AppRoute.COUNTER_EXERCISE,
        titleRes = R.string.counter_exercise_title,
        descriptionRes = R.string.counter_exercise_desc,
        categoryRes = R.string.category_state_machine
    ),
    PracticeApp(
        id = AppRoute.DESSERT_CLICKER,
        titleRes = R.string.dessert_clicker_title,
        descriptionRes = R.string.dessert_clicker_desc,
        categoryRes = R.string.category_state_machine
    ),
    PracticeApp(
        id = AppRoute.LAZY_GRID_PRACTICE,
        titleRes = R.string.lazy_grid_practice_title,
        descriptionRes = R.string.lazy_grid_practice_desc,
        categoryRes = R.string.category_grids
    )
)
