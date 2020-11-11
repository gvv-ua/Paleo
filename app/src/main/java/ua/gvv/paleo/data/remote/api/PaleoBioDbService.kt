package ua.gvv.paleo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ua.gvv.paleo.data.remote.api.response.TaxaResponse
import ua.gvv.paleo.data.remote.api.response.TaxonImageResponse

interface PaleoBioDbService {
    @GET("taxa/single.json?show=full,parent")
    suspend fun getTaxonById(@Query("taxon_id") taxonId: String): Response<TaxaResponse>

    //rank
    @GET("taxa/list.json?rel=children")
    suspend fun getTaxonChildList(@Query("taxon_id") taxonId: String): Response<TaxaResponse>

    @GET("taxa/thumb.json")
    suspend fun getTaxonImage(@Query("taxon_id") taxonId: String): Response<TaxonImageResponse>
}