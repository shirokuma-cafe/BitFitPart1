package com.example.bitfitpart1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class ItemAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.log_layout, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val date = itemView.findViewById<TextView>(R.id.dateLog)
        private val time = itemView.findViewById<TextView>(R.id.timeLog)
        private val amount = itemView.findViewById<TextView>(R.id.amountLog)
        private val mL = itemView.findViewById<TextView>(R.id.mL)

        fun bind(item: Item){
            date.text = item.date
            time.text = item.time
            amount.text = item.amount.toString()
            mL.text
        }
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}



