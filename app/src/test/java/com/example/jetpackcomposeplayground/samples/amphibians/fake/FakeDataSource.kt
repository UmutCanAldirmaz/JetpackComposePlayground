package com.example.jetpackcomposeplayground.samples.amphibians.fake

import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian

object FakeDataSource {
    val amphibiansList = listOf(
        Amphibian(
            name = "Great Basin Spadefoot",
            type = "Toad",
            description = "This toad is typically found in desert conditions.",
            imgSrc = "https://example.com/spadefoot.jpg"
        ),
        Amphibian(
            name = "California Newt",
            type = "Salamander",
            description = "Known for its bright orange belly.",
            imgSrc = "https://example.com/newt.jpg"
        )
    )
}
