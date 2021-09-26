package com.example.imdb.ui

import android.R.attr
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentSignupBinding
import com.example.imdb.viewmodels.LoginFragmentViewModel
import com.example.imdb.viewmodels.SignupFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.R.attr.textColor

import android.widget.TextView
import com.example.imdb.Apputils.Resource


class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding
    private val viewmodel2 : SignupFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        binding.viewModel2 = viewmodel2
        binding.lifecycleOwner = viewLifecycleOwner
        binding.signinTxt.setOnClickListener{
            findNavController().popBackStack()
        }
        viewmodel2.response.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                    binding.progressBar2.visibility = View.VISIBLE
                    binding.signupBtn.text = "Creating account"

                }
                is Resource.Error ->{
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(requireContext(),  it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{

                        Snackbar.make(
                            binding.root, "Account Created Successfully", Snackbar.LENGTH_SHORT
                        ).setTextColor(resources.getColor(R.color.white)).setBackgroundTint(resources.getColor(R.color.yellow)).show()
                    binding.progressBar2.visibility = View.GONE
                    binding.signupBtn.text = "Account Created"

                }
            }
        }
        viewmodel2.temp.observe(viewLifecycleOwner)
        {
            it?.let {
                if (!it) {
                    viewmodel2.check1.observe(viewLifecycleOwner)
                    {
                        it?.let {
                            if (it.isEmpty()) {
                                    Snackbar.make(
                                        binding.root,

                                        "Account Created Successfully", Snackbar.LENGTH_SHORT
                                    ).setTextColor(resources.getColor(R.color.white)).setBackgroundTint(resources.getColor(R.color.yellow)).show()
                                viewmodel2.check1.postValue(null)
                            } else {
                                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                                viewmodel2.check1.postValue(null)
                            }
                        }
                    }
                }
            }

        }



    }







}