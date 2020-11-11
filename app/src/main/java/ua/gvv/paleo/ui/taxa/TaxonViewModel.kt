package ua.gvv.paleo.ui.taxa

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import ua.gvv.paleo.data.remote.api.Error
import ua.gvv.paleo.data.remote.api.Success
import ua.gvv.paleo.data.remote.api.response.Taxon
import ua.gvv.paleo.data.repository.TaxonRepository
import ua.gvv.paleo.util.DispatchersProvider
import ua.gvv.paleo.util.toNetworkResponse

class TaxonViewModel(
    private val repository: TaxonRepository,
    private val dispatchersProvider: DispatchersProvider
): ViewModel() {

    private val _taxon = MutableLiveData<Taxon?>()
    val taxon: LiveData<Taxon?>
    get() = _taxon

    private val _children = MutableLiveData<List<Taxon>>()
    val children: LiveData<List<Taxon>>
        get() = _children

    private val _taxonImage = MutableLiveData<String>()
    val taxonImage: LiveData<String>
    get() = _taxonImage

    fun setTaxonId(id: String) {
        getTaxonById(id)
        getTaxonChildList(id)
        getTaxonImageInfo(id)
    }

    private fun getTaxonById(id: String) = viewModelScope.launch {
        val response = withContext(dispatchersProvider.io) {
            repository.getTaxonById(id).toNetworkResponse()
        }
        when (response) {
            is Success -> {
                val items = response.data.taxa
                if (items.size == 1) {
                    _taxon.postValue(items[0])
                }
            }
            is Error -> {

            }
        }
    }

    private fun getTaxonChildList(id: String) = viewModelScope.launch {
        val response = withContext(dispatchersProvider.io) {
            repository.getTaxonChildList(id).toNetworkResponse()
        }
        when (response) {
            is Success -> {
//                _children.postValue(response.data.taxa.sortedBy { it.name })
                _children.postValue(response.data.taxa)
            }
            is Error -> {

            }
        }
    }

    private fun getTaxonImageInfo(id: String) = viewModelScope.launch {
        val response = withContext(dispatchersProvider.io) {
            repository.getTaxonImageInfo(id).toNetworkResponse()
        }
        when (response) {
            is Success -> {
                val items = response.data.taxa
                if (items.size == 1) {
                    getTaxonImage(items[0].uid)
                }
            }
            is Error -> {
            }
        }
    }

    private fun getTaxonImage(uid: String) = viewModelScope.launch {
        Timber.tag("TAXA").d("getTaxaSvgImage: $uid")
        val response = withContext(dispatchersProvider.io) {
            repository.getTaxonSvgImage(uid).toNetworkResponse()
        }
        when (response) {
            is Success -> {
                val url = response.data.image.svgFile?.fullUrl ?: response.data.image.pngFiles[0].fullUrl
                _taxonImage.postValue(url)
            }
            is Error -> {

            }
        }
    }

    fun setParentTaxon() {
        taxon.value?.parentId?.let { id ->
            setTaxonId(id)
        }
    }
}