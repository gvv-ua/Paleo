package ua.gvv.paleo.data.repository

import retrofit2.Response
import ua.gvv.paleo.data.remote.api.PaleoBioDbService
import ua.gvv.paleo.data.remote.api.PhylopicService
import ua.gvv.paleo.data.remote.api.response.PhylopicImageResponse
import ua.gvv.paleo.data.remote.api.response.TaxaResponse
import ua.gvv.paleo.data.remote.api.response.TaxonImageResponse

class TaxonRepository(
    private val paleoService: PaleoBioDbService,
    private val phylopicService: PhylopicService
) {

    suspend fun getTaxonById(id: String): Response<TaxaResponse> = paleoService.getTaxonById(id)

    suspend fun getTaxonChildList(id: String): Response<TaxaResponse> =
        paleoService.getTaxonChildList(id)

    suspend fun getTaxonImageInfo(id: String): Response<TaxonImageResponse> =
        paleoService.getTaxonImage(id)

    suspend fun getTaxonSvgImage(uid: String): Response<PhylopicImageResponse> =
        phylopicService.getTaxonImage(uid)
}