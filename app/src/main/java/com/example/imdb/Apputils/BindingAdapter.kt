package com.example.imdb.Apputils

import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("set_error")
fun error(textInputLayout: TextInputEditText , error: String?)
{
    error?.let {
        Log.e("error", error)
        if (error!="")
        {

            textInputLayout.error = error
        }
    }

}

/*
@BindingAdapter("isErrorEnabled")
fun errorEnable(textInputLayout: TextInputEditText , boolean: Boolean){
    if (boolean){
        textInputLayout.isenabled
    }

}*/
