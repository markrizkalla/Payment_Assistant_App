package com.example.gymapp.ui.detail

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.R
import com.example.gymapp.adapter.PaymentAdapter
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val subscribersDao = SubscribersDatabase.getInstance(application).subscribersDao
        val paymentDao = SubscribersDatabase.getInstance(application).paymentDao

        val subId = DetailFragmentArgs.fromBundle(requireArguments()).subId

        val detailViewModelFactory = DetailViewModelFactory(subscribersDao,paymentDao,subId)
        val detailViewModel = ViewModelProvider(this,detailViewModelFactory).get(DetailViewModel::class.java)

        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (subId != -1){
            binding.insertSub.text = "Update"
            binding.historyButton.visibility = View.VISIBLE
            binding.deleteButton.visibility= View.VISIBLE
            binding.payButton.visibility = View.VISIBLE
            detailViewModel._edit.value = true

        }


        binding.historyButton.setOnClickListener {
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.user_payment_dialog, null)
            val builder = AlertDialog.Builder(requireActivity(), R.style.Theme_GymApp)
            builder.setCancelable(true)
            builder.setTitle("PAYMENT HISTORY")
            val recycler = dialogView.findViewById<RecyclerView>(R.id.recycler_user_payments)
            recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            detailViewModel.history()

            detailViewModel.history.observe(viewLifecycleOwner, Observer {
                val  adapter = it?.let { it1 -> PaymentAdapter(application, it1.payments) }
                recycler.adapter = adapter

            })

            builder.setView(dialogView)
            dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.CYAN))
            dialog.show()

        }
        binding.insertSub.setOnClickListener {
            val name = binding.subName.text.toString()
            val subDate = binding.subDate.text.toString()
            val subEndDate = binding.subEndDate.text.toString()
            val price = binding.subPrice.text.toString()

            detailViewModel.update(name,subDate,subEndDate,price)

            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        detailViewModel.navigateToHome.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
                detailViewModel.onNavigateToHome()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}