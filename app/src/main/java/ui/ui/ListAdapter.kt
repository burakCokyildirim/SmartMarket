package ui.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yazlab.smartmarket.R
import kotlinx.android.synthetic.main.item_campain.view.*

class ListAdapter() : RecyclerView.Adapter<ListAdapter.CampainListViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CampainListViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_campain, p0, false)
        return CampainListViewHolder(v)
    }

    override fun getItemCount(): Int {

        return User.campains.size
    }

    override fun onBindViewHolder(p0: CampainListViewHolder, p1: Int) {
        p0.itemView.name.text = User.campains[p1].name
        p0.itemView.category.text = User.campains[p1].category
        p0.itemView.distance.text = User.campains[p1].distance.toString()

    }


    class CampainListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

