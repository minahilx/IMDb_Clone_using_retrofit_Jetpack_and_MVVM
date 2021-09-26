package com.example.imdb.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.imdb.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragmentViewModel() : ViewModel() {
    var out: MutableLiveData<Boolean?> = MutableLiveData(false)



    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        out.value = true
    }


}













