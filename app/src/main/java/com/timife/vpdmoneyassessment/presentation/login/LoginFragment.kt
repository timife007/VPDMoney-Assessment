package com.timife.vpdmoneyassessment.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentLoginBinding
import com.timife.vpdmoneyassessment.navigation.Dashboard

class LoginFragment() : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)

        loginBinding.loginButton.setOnClickListener {
            navigateToDashboard()
        }

        return loginBinding.root
    }


    private fun navigateToDashboard() {
        findNavController().navigate(Dashboard)
    }
}