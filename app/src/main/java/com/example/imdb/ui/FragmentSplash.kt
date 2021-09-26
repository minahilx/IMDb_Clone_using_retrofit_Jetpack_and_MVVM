package com.example.imdb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import android.os.Handler
import android.util.Log
import com.example.imdb.R


class FragmentSplash : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            FirebaseAuth.getInstance().currentUser?.let {
                Log.e("current", "not null")
                findNavController().navigate(R.id.action_fragmentSplash_to_fragmentHome) //if user already signed in then redirect to home screen


            } ?: run {
                findNavController().navigate(R.id.action_fragmentSplash_to_loginFragment) //if user not signed in then redirect to login screen
                Log.e("current", "null")
            }
        }, 3000) //for 4 seconds

    }


}