package com.example.imdb.viewmodels

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.Apputils.Resource
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.handleCoroutineException

class LoginFragmentViewModel : ViewModel() {

    val response = MutableLiveData<Resource<Boolean>>()
    var check: MutableLiveData<String?> = MutableLiveData(null)
    var email: String = ""
    var password: String = ""
    var emailError: MutableLiveData<String> = MutableLiveData("")
    var passwordError: MutableLiveData<String> = MutableLiveData(null)
    var emptyAll: MutableLiveData<String> = MutableLiveData("")
  //  var emailLink: MutableLiveData<Boolean> = MutableLiveData(true)

    fun login() {
        Log.e("values ", "$password $email")
       if (email.isEmpty() && password.isEmpty()) {
                emptyAll.value = "All fields are required"
            }
       if ( email.isEmpty())
           {
                emailError.value = "email is required"
            }

           if (password.isEmpty())  {
                passwordError.value = "password is required"
            }
           else  {
               response.postValue(Resource.Loading())
               FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    email,
                    password
                ).addOnSuccessListener {
               //    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                       response.postValue(Resource.Success(true))
                       check.value = ""
                 //  } else {
                 //      FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                  //     FirebaseAuth.getInstance().signOut()
              //     }
                }.addOnFailureListener {
                    check.value = it.message
                   response.postValue(Resource.Error(it.message))
               }
            }
        }
    }


/*



addOnSuccessListener {
                      check.value = ""
                   if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        response.postValue(Resource.Success(true))

                        check.value = ""
                  } else {
                       FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        FirebaseAuth.getInstance().signOut()
                       emailLink.value = false
                    }

                }
 */
