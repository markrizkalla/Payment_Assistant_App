package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.gymapp.R
import com.example.gymapp.database.PaymentsDatabase
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val subscribersDao = SubscribersDatabase.getInstance(application).subscribersDao
        val paymentDao = PaymentsDatabase.getInstance(application).paymentDao

        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId

        val detailViewModelFactory = DetailViewModelFactory(subscribersDao,paymentDao,subId)
        val detailViewModel = ViewModelProvider(this,detailViewModelFactory).get(DetailViewModel::class.java)

        if (subId != -1){
            binding.insertSub.text = "Update"
            detailViewModel._edit.value = true
            detailViewModel.subscriber.observe(viewLifecycleOwner, Observer {
                binding.subName.setText(it.name)
                binding.subDate.setText(it.subDate)
                binding.subEndDate.setText(it.sebEndDate)
                binding.subPrice.setText(it.subPrice)
            })
            detailViewModel.payment.observe(viewLifecycleOwner, Observer {

            })
        }

        binding.insertSub.setOnClickListener {
            val name = binding.subName.text.toString()
            val subDate = binding.subDate.text.toString()
            val subEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()

            detailViewModel.update(name,subDate,subEndDate,price)

            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}