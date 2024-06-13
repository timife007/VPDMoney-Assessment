package com.timife.vpdmoneyassessment.presentation.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.timife.vpdmoneyassessment.databinding.FragmentSignupBinding
import com.timife.vpdmoneyassessment.navigation.Login
import com.timife.vpdmoneyassessment.presentation.auth.Validation

class SignupFragment : Fragment() {

    private lateinit var signupBinding: FragmentSignupBinding


    private val viewModel: SignupViewModel by viewModels()

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize firebase auth
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupBinding = FragmentSignupBinding.inflate(inflater, container, false)
        val email = signupBinding.signupEmailInputText.text
        val password = signupBinding.signupPasswordInputText.text
        val confirmPassword = signupBinding.confirmPasswordInputText.text

        signupBinding.createAcctLayout.setOnClickListener {
            signup(email.toString(), password.toString(), confirmPassword.toString())
        }
        signupBinding.loginText.setOnClickListener {
            findNavController().navigate(Login)
        }
        findNavController().popBackStack()
        return signupBinding.root
    }

    private fun signup(email: String, password: String, confirmPassword: String) {
        Validation.areSignupCredentialsValid(
            email,
            password,
            confirmPassword,
            isEmailValid = { isEmailValid, message ->
                if (!isEmailValid) {
                    signupBinding.signupEmailInputLayout.error = message
                } else {
                    signupBinding.signupEmailInputLayout.error = null
                }
            },
            isPasswordValid = { isPasswordValid, message, isConfirmValid ->
                if (!isPasswordValid && isConfirmValid) {
                    signupBinding.signupPasswordInputLayout.error = message
                } else if(isPasswordValid && !isConfirmValid){
                        signupBinding.confirmPasswordInputLayout.error = message
                        signupBinding.signupPasswordInputLayout.error = null
                }else {
                    signupBinding.signupPasswordInputLayout.error = null
                    signupBinding.confirmPasswordInputLayout.error = null
                }
            },
            allValidated = {
                signupBinding.signupProgress.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            if (user != null) {
                                findNavController().navigate(Login)
                                Toast.makeText(
                                    requireContext(),
                                    "Account successfully created",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Error creating user",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                requireActivity(),
                                task.exception?.message,
                                Toast.LENGTH_LONG,
                            ).show()
                        }
                        signupBinding.signupProgress.visibility = View.GONE
                    }
            })
    }
}