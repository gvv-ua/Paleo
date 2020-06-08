package ua.gvv.paleo.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhylopicImageResponse(
    @Json(name = "result") val image: PhylopicImage
)

@JsonClass(generateAdapter = true)
data class PhylopicImage(
    @Json(name = "svgFile") val svgFile: PhylopicSvgFile?,
    @Json(name = "pngFiles") val pngFiles: List<PhylopicPngFile>
)

@JsonClass(generateAdapter = true)
data class PhylopicSvgFile(
    @Json(name = "url") val url: String
) {
    val fullUrl: String
    get() = "http://phylopic.org$url"
}

@JsonClass(generateAdapter = true)
data class PhylopicPngFile(
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int
) {
    val fullUrl: String
        get() = "http://phylopic.org$url"
}