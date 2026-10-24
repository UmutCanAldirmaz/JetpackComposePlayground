package com.example.jetpackcomposeplayground.home

import com.example.jetpackcomposeplayground.R

val practiceAppList = listOf(
    // === UNIT 2: Temel Compose, Layouts & State ===
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

    // === UNIT 3: Display Lists & Material Design ===
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

    // === UNIT 4: Architecture Components & Lifecycle ===
    PracticeApp(
        id = AppRoute.DESSERT_CLICKER,
        titleRes = R.string.dessert_clicker_title,
        descriptionRes = R.string.dessert_clicker_desc,
        categoryRes = R.string.category_state_machine
    ),
    PracticeApp(
        id = AppRoute.COUNTER_EXERCISE,
        titleRes = R.string.counter_exercise_title,
        descriptionRes = R.string.counter_exercise_desc,
        categoryRes = R.string.category_state_machine
    ),

    // === ADVANCED LISTS, GRIDS, ANIMATIONS & STATE ===
    PracticeApp(
        id = AppRoute.LAZY_GRID_PRACTICE,
        titleRes = R.string.lazy_grid_practice_title,
        descriptionRes = R.string.lazy_grid_practice_desc,
        categoryRes = R.string.category_grids
    ),
    PracticeApp(
        id = AppRoute.LAZY_LIST_STATE_PRACTICE,
        titleRes = R.string.lazy_list_state_practice_title,
        descriptionRes = R.string.lazy_list_state_practice_desc,
        categoryRes = R.string.category_grids
    ),
    PracticeApp(
        id = AppRoute.ADVANCED_LAZY_LISTS_PRACTICE,
        titleRes = R.string.advanced_lazy_lists_practice_title,
        descriptionRes = R.string.advanced_lazy_lists_practice_desc,
        categoryRes = R.string.category_advanced_lists
    ),
    PracticeApp(
        id = AppRoute.ORDER_HISTORY,
        titleRes = R.string.order_history_title,
        descriptionRes = R.string.order_history_desc,
        categoryRes = R.string.category_advanced_lists
    ),
    PracticeApp(
        id = AppRoute.DYNAMIC_LIST_ANIMATIONS,
        titleRes = R.string.dynamic_animations_title,
        descriptionRes = R.string.dynamic_animations_desc,
        categoryRes = R.string.category_animations
    ),

    // === LAB & SANDBOX ===
    PracticeApp(
        id = AppRoute.EXERCISE,
        titleRes = R.string.exercise_title,
        descriptionRes = R.string.exercise_desc,
        categoryRes = R.string.category_sandbox
    )
)
