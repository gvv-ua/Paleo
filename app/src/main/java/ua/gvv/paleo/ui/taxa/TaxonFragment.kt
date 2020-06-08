package ua.gvv.paleo.ui.taxa

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import coil.api.load
import kotlinx.android.synthetic.main.fragment_taxon.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import ua.gvv.paleo.R
import ua.gvv.paleo.data.remote.api.response.Taxon

class TaxonFragment: Fragment(R.layout.fragment_taxon) {

    private val viewModel by viewModel<TaxonViewModel>()

    private var adapter: TaxonAdapter? = null

    private val taxonSelector: TaxonSelector = { taxon ->
        viewModel.setTaxonId(taxon.originalId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListeners()
//        viewModel.setTaxonId(91486)
        viewModel.setTaxonId("txn:36651")
//        viewModel.setTaxonId("txn:69296")
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        taxonChildList.adapter = null
        adapter = null
    }

    private fun initAdapter() {
        adapter = TaxonAdapter(taxonSelector)
        taxonChildList.apply {
            adapter = this@TaxonFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun initObservers() {
        viewModel.taxa.observe(viewLifecycleOwner, Observer { taxa ->
            taxa?.let { updateTaxaUI(it) }
        })
        viewModel.children.observe(viewLifecycleOwner, Observer { items ->
            adapter?.submitList(items)
        })
        viewModel.taxonImage.observe(viewLifecycleOwner, Observer { url ->
            taxonImage.load(url)
        })
    }

    private fun updateTaxaUI(taxon: Taxon) {
        Timber.tag("Taxa").d("taxon: $taxon")
        taxonName.text = taxon.name
        taxonRank.text = taxon.rank.toString()
        taxonIsExtant.text = taxon.isExtant.toString()
        taxonFossilOccurrencesNumber.text = taxon.fossilOccurrencesNumber.toString()
    }

    private fun initListeners() {
//        taxaItemButton.setOnClickListener {
//            viewModel.getTaxaById(91486)
//        }
//        taxaChildrenButton.setOnClickListener {
//            viewModel.getTaxaChildrenList(91486)
//        }
    }
}