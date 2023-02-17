package com.example.gymapp.ui.home

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gymapp.R
import com.example.gymapp.dao.SubscribersDao

class HomeViewModel(dao: SubscribersDao):ViewModel() {

    var subLists = dao.getAll()

     fun navigate(view: ConstraintLayout) {
         view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}