package com.example.gymapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.databinding.SubItemBinding
import com.example.gymapp.model.Subscriber
import com.example.gymapp.ui.home.HomeFragmentDirections

class SubsAdapter(val context: Context, val subList: List<Subscriber>) : RecyclerView.Adapter<SubsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SubItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subscriber = subList[position]
        holder.name.text = subscriber.name
        holder.date.text = subscriber.sebEndDate
        holder.price.text = subscriber.subPrice

        holder.itemView.setOnClickListener {
            val id = subscriber.subscriber_id
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            action.subId = id
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return subList.size
    }

    class ViewHolder(binding: SubItemBinding) :RecyclerView.ViewHolder(binding.root){
        val name = binding.name
        val date = binding.endDate
        val price = binding.price
    }
}