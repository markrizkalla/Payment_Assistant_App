package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding
import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var edit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = SubscribersDatabase.getInstance(application).subscribersDao


        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId
        if (subId != 0){
            edit = true
            GlobalScope.launch{
                val subscriber = dao.get(subId)
                binding.subName.setText(subscriber.name)
                binding.subDate.setText(subscriber.subDate)
                binding.subEndDate.setText(subscriber.sebEndDate)
                binding.subPrice.setText(subscriber.subPrice)
            }


        }


        binding.insertSub.setOnClickListener {
            val name = binding.subName.text.toString()
            val subDate = binding.subDate.text.toString()
            val subEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()


            if (edit){
                GlobalScope.launch {
                    val subscriber = dao.get(subId)
                    subscriber.name = name
                    subscriber.subDate = subDate
                    subscriber.sebEndDate = subEndDate
                    subscriber.subPrice = price
                    dao.update(subscriber)
                }
                edit = false
            }else{
                val subscriber = Subscriber(name = name, sebEndDate = subEndDate, subDate = subDate, subPrice = price)
                GlobalScope.launch {
                    dao.insert(subscriber)
                }
            }

        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}