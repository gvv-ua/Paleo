package ua.gvv.paleo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.gvv.paleo.data.remote.api.response.PhylopicImageResponse
import ua.gvv.paleo.data.remote.api.response.TaxaResponse
import ua.gvv.paleo.data.remote.api.response.TaxonImageResponse

interface PhylopicService {
    @GET("image/{uid}?options=svgFile+pngFiles")
    suspend fun getTaxonImage(@Path("uid") uid: String): Response<PhylopicImageResponse>
}