package com.timife.vpdmoneyassessment.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentDashboardBinding
import com.timife.vpdmoneyassessment.navigation.AccountManagement
import com.timife.vpdmoneyassessment.navigation.Transfer

class DashboardFragment : Fragment() {
    private lateinit var dashboardBinding: FragmentDashboardBinding

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        val recyclerView = dashboardBinding.dashboardRecyclerView
        dashboardBinding.transferButton.setOnClickListener { navigateToTransfer() }
        dashboardBinding.accountsButton.setOnClickListener { navigateToAccounts() }

        // Set the adapter
        with(recyclerView) {
            adapter = DashboardHistoryAdapter(mockTransaction)
        }
        return dashboardBinding.root
    }

    private fun navigateToTransfer(){
        findNavController().navigate(Transfer)
    }

    private fun navigateToAccounts(){
        findNavController().navigate(AccountManagement)
    }
}