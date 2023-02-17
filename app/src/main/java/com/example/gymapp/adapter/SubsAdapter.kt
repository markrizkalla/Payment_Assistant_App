package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.model.Subscriber

class SubsAdapter(val context: Context, val subList: List<Subscriber>) : RecyclerView.Adapter<SubsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sub_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var subscriber = subList[position]
        holder.name.text = subscriber.name
        holder.date.text = subscriber.sebEndDate
    }

    override fun getItemCount(): Int {
        return subList.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val date = itemView.findViewById<TextView>(R.id.endDate)
    }
}