package com.example.imdb.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.Apputils.Resource
import com.example.imdb.R
import com.example.imdb.databinding.FargmentLoginBinding
import com.example.imdb.viewmodels.LoginFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(R.layout.fargment_login) {
    private lateinit var binding: FargmentLoginBinding
    private val viewmodel: LoginFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FargmentLoginBinding.bind(view)
        binding.viewModel = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }


        viewmodel.response.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.loginBtn.text = "please wait"

                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),  it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.loginBtn.text = "Done"

                       findNavController().navigate(R.id.action_loginFragment_to_fragmentHome2)




                }
            }
        }


        

    }




}

