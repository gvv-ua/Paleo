package ua.gvv.paleo.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxonImageResponse(
    @Json(name = "records") val taxa: List<TaxonImage>
)

@JsonClass(generateAdapter = true)
data class TaxonImage(
    @Json(name = "oid") val originalId: String,
    @Json(name = "uid") val uid: String
)
