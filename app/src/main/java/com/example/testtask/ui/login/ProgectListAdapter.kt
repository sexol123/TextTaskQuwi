package com.example.testtask.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.data.response.Project
import kotlinx.android.synthetic.main.item_prject_list.view.*

class ProgectListAdapter(
    val itemList: ArrayList<Project>,
    private val onItemClick: (Project) -> Unit = {}
) : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (viewType) {
            else -> VH(
                LayoutInflater.from(parent.context)
                    .inflate(VH.layoutId, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item  = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun update(newItems: List<Project>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }
}

class VH(v: View) : RecyclerView.ViewHolder(v) {
    companion object {
        const val layoutId: Int = R.layout.item_prject_list
    }

    fun bind(item: Project) {
        with(itemView) {

            Glide.with(context)
                .load(item.logo_url)
                .placeholder(R.drawable.quwi_logo)
                .error(R.drawable.ic_launcher_background)
                .into(image)

            text_info.text = item.name
        }
    }

}
