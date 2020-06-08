package ua.gvv.paleo.ui.taxa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_taxon.view.*
import ua.gvv.paleo.R
import ua.gvv.paleo.data.remote.api.response.Taxon

typealias TaxonSelector = (Taxon) -> Unit
class TaxonAdapter(
    private val selector: TaxonSelector
): ListAdapter<Taxon, TaxonAdapter.ViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_taxon, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition < itemCount) {
                    selector(getItem(adapterPosition))
                }
            }
        }

        fun bind(item: Taxon) {
            itemView.itemTaxonName.text = item.name
            val url = "https://paleobiodb.org/data1.2/taxa/thumb.png?taxon_id=${item.id}"
            itemView.itemTaxonIcon.load(url)
        }
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<Taxon>() {
            override fun areItemsTheSame(oldItem: Taxon, newItem: Taxon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Taxon, newItem: Taxon): Boolean {
                return oldItem == newItem
            }
        }
    }
}