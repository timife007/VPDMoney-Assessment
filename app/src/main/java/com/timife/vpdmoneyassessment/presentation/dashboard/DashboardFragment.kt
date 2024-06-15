package com.timife.vpdmoneyassessment.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.data.mocks.mockAccounts
import com.timife.vpdmoneyassessment.databinding.FragmentDashboardBinding
import com.timife.vpdmoneyassessment.navigation.AccountManagement
import com.timife.vpdmoneyassessment.navigation.Transfer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        var totalBalance = 0
        mockAccounts.forEach {
            totalBalance += it.accountBalance
        }
        dashboardBinding.totalAcctBalance.text = totalBalance.toString()

        viewModel.transactions.observe(viewLifecycleOwner) {
            if (it.loading) {
                dashboardBinding.transactionListProgress.visibility = View.VISIBLE
            } else if (it.data != null) {
                with(recyclerView) {
                    adapter = DashboardHistoryAdapter(it.data)
                }
                dashboardBinding.transactionListProgress.visibility = View.GONE
            } else {
                dashboardBinding.transactionListProgress.visibility = View.GONE
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
            }
        }
        return dashboardBinding.root
    }

    private fun navigateToTransfer() {
        findNavController().navigate(Transfer)
    }

    private fun navigateToAccounts() {
        findNavController().navigate(AccountManagement)
    }
}