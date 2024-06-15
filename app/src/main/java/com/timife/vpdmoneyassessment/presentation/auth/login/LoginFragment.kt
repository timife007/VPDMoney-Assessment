package com.timife.vpdmoneyassessment.presentation.auth.login

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
import com.timife.vpdmoneyassessment.databinding.FragmentLoginBinding
import com.timife.vpdmoneyassessment.navigation.Dashboard
import com.timife.vpdmoneyassessment.navigation.Signup
import com.timife.vpdmoneyassessment.presentation.auth.Validation.areLoginCredentialsValid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        val email = loginBinding.loginEmailInputText.text
        val password = loginBinding.loginPasswordInputText.text

        loginBinding.loginLayout.setOnClickListener {
            login(email.toString(), password.toString())
        }

        loginBinding.createAcctText.setOnClickListener {
            findNavController().navigate(Signup)
        }
        findNavController().popBackStack()

        return loginBinding.root
    }


    private fun login(email: String, password: String) {
        areLoginCredentialsValid(email, password, isPasswordValid = { isValid, message ->
            if (!isValid) {
                loginBinding.loginPasswordInputLayout.error = message
            } else {
                loginBinding.loginPasswordInputLayout.error = null
            }
        }, isEmailValid = { isValid, message ->
            if (!isValid) {
                loginBinding.loginEmailInputLayout.error = message
            } else {
                loginBinding.loginEmailInputLayout.error = null
            }
        }, allValidated = {
            loginBinding.loginProgress.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Toast.makeText(
                            requireActivity(),
                            "Login Successful",
                            Toast.LENGTH_LONG
                        ).show()
                        navigateToDashboard()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            requireActivity(),
                            task.exception?.message,
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                    loginBinding.loginProgress.visibility = View.GONE
                }
        })
    }


    private fun navigateToDashboard() {
        findNavController().navigate(Dashboard)
    }
}