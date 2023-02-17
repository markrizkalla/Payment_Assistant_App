package com.example.gymapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var edit = false

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
        if (subId != -1){
            edit = true
            GlobalScope.launch{
                val subscriber = subscribersDao.get(subId)
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
                    val subscriber = subscribersDao.get(subId)
                    val payment = paymentDao.get(subId)

                    payment.name = name
                    payment.subDate = subDate
                    payment.subPrice = price

                    subscriber.name = name
                    subscriber.subDate = subDate
                    subscriber.sebEndDate = subEndDate
                    subscriber.subPrice = price
                    subscribersDao.update(subscriber)
                    paymentDao.update(payment)
                }
                edit = false
            }else{
                val subscriber = Subscriber(name = name, sebEndDate = subEndDate, subDate = subDate, subPrice = price)
                val payment = Payment(subscriber_id = subscriber.subscriber_id,name=name,subDate=subDate, subPrice = price)
                GlobalScope.launch {
                    subscribersDao.insert(subscriber)
                    paymentDao.insert(payment)
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