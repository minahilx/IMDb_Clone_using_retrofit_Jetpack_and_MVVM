package com.example.imdb.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.Apputils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupFragmentViewModel : ViewModel() {

    val response = MutableLiveData<Resource<Boolean>>()
    var check1: MutableLiveData<String?> = MutableLiveData("")
    var email: String = ""
    var password: String = ""
    var name: String = ""
    var emailError: MutableLiveData<String> = MutableLiveData("")
    var passwordError: MutableLiveData<String> = MutableLiveData("")
    var nameError: MutableLiveData<String> = MutableLiveData("")
    var emptyAll: MutableLiveData<String> = MutableLiveData("")
    var emailFormatError: MutableLiveData<String> = MutableLiveData("")
    var temp: MutableLiveData<Boolean> = MutableLiveData(true)
    var emailExist: MutableLiveData<String> = MutableLiveData("")
    fun signUp() {
        Log.e("values ", "$password $email")
        if(email.isEmpty() && password.isEmpty() && name.isEmpty())
           {
                emptyAll.value = "All fields are required"
            }
           if ( email.isEmpty()) {
                emailError.value = "email is required"
            }
             if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailFormatError.value = "please provide valid email"
            }
             if(password.isEmpty()){
                passwordError.value = "password is required"

            }
             if(name.isEmpty()){
                nameError.value = "name is required"
            }
            else {
                checkEmail(email)
                 response.postValue(Resource.Loading())
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email, password
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val ob = HashMap<String, Any>()
                            ob["name"] = name
                            ob["email"] = email
                            ob["password"] = password
                            FirebaseFirestore.getInstance().collection("users").add(ob)
                                .addOnSuccessListener {
                                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                                    check1.value = ""
                                    temp.value = false
                                    response.postValue(Resource.Success(true))

                                }.addOnFailureListener {
                                    check1.value = it.message
                                    temp.value = false
                                    response.postValue(Resource.Error(it.message))
                                }
                        }
                    }
            }
        }

    fun checkEmail(email: String) {
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener()
        {
            if (it.result?.signInMethods?.isEmpty() == true) {
                emailExist.value = "Email already exist"
            }
        }
    }
}
