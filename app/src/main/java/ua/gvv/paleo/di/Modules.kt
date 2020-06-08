package ua.gvv.paleo.di

import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.gvv.paleo.data.remote.api.PaleoBioDbService
import ua.gvv.paleo.data.remote.api.PhylopicService
import ua.gvv.paleo.data.remote.api.RetrofitFactory
import ua.gvv.paleo.data.remote.api.RetrofitFactory.PALEO_API_URL
import ua.gvv.paleo.data.remote.api.RetrofitFactory.PHYLOPIC_API_URL
import ua.gvv.paleo.data.repository.TaxonRepository
import ua.gvv.paleo.ui.taxa.TaxonViewModel
import ua.gvv.paleo.util.AppDispatcher
import ua.gvv.paleo.util.DispatchersProvider

private val dataModule = module {
    single { Moshi.Builder().build() }
    single<PaleoBioDbService> { RetrofitFactory.createService(get(), get(), PALEO_API_URL) }
    single<PhylopicService> { RetrofitFactory.createService(get(), get(), PHYLOPIC_API_URL) }
    single<DispatchersProvider> { AppDispatcher() }

    single { TaxonRepository(get(), get()) }
}

private val viewModelModule = module {
    viewModel { TaxonViewModel(get(), get()) }
}

val paleoModules = listOf(dataModule, viewModelModule)