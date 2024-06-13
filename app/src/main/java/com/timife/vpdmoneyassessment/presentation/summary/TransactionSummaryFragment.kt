package com.timife.vpdmoneyassessment.presentation.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentTransactionSummaryBinding
import com.timife.vpdmoneyassessment.navigation.Dashboard

class TransactionSummaryFragment : Fragment() {
    private lateinit var summaryBinding: FragmentTransactionSummaryBinding

    private val viewModel: TransactionSummaryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        summaryBinding = FragmentTransactionSummaryBinding.inflate(inflater,container, false)
        summaryBinding.confirmButton.setOnClickListener {
            navigateToDashboard()
        }
        return summaryBinding.root
    }

    private fun navigateToDashboard() {
        findNavController().navigate(Dashboard)
    }
}