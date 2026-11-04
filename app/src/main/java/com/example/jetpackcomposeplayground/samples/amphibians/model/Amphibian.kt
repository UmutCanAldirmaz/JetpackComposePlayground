package com.example.jetpackcomposeplayground.samples.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing an Amphibian fetched from the network API.
 */
@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src")
    val imgSrc: String
)
