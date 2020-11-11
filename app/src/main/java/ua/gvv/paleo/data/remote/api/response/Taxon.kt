package ua.gvv.paleo.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxaResponse(
    @Json(name = "records") val taxa: List<Taxon>
)

@JsonClass(generateAdapter = true)
data class Taxon(
    @Json(name = "oid") val originalId: String,
    @Json(name = "rnk") val rank: Int,
    @Json(name = "nam") val name: String,
    @Json(name = "par") val parentId: String?,
    @Json(name = "prl") val parentName: String?,
    @Json(name = "rid") val reference: String,
    @Json(name = "ext") val isExtant: Int?,
    @Json(name = "noc") val fossilOccurrencesNumber: Int,

    @Json(name = "tei") val earlyInterval: String?,
    @Json(name = "tli") val lateInterval: String?,

    @Json(name = "kgl") val kingdom: String?,
    @Json(name = "phylum") val phylum: String?,
    @Json(name = "cll") val className: String?,
    @Json(name = "odl") val order: String?,
    @Json(name = "fml") val family: String?,
    @Json(name = "gnl") val genus: String?

) {
    val id: Long
    get() = originalId.substring(originalId.lastIndexOf(':') + 1).toLong()
}

