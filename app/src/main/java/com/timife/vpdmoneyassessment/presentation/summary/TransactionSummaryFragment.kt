package com.timife.vpdmoneyassessment.presentation.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentTransactionSummaryBinding
import com.timife.vpdmoneyassessment.navigation.Dashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionSummaryFragment : Fragment() {
    private lateinit var summaryBinding: FragmentTransactionSummaryBinding

    private val viewModel: TransactionSummaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        summaryBinding = FragmentTransactionSummaryBinding.inflate(inflater, container, false)
        observeDate()

        summaryBinding.apply {
            confirmLayout.setOnClickListener {
                viewModel.makeTransfer()
            }
            summaryNavBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return summaryBinding.root
    }

    private fun observeDate() {
        viewModel.transferState.observe(viewLifecycleOwner){
            if (it.loading){
                summaryBinding.confirmProgress.visibility = View.VISIBLE
            }else if(!it.error.isNullOrEmpty()){
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                summaryBinding.confirmProgress.visibility = View.GONE
            }else if(it.success){
                navigateToDashboard()
            }
        }
        viewModel.senderDetails.observe(viewLifecycleOwner) {
            summaryBinding.apply {
                sourceAcctName.text = buildString {
                    append(it.accountFirstName)
                    append(" ")
                    append(it.accountLastName)
                }
                srcAcctNo.text = it.accountNumber
            }
        }

        viewModel.receiverDetails.observe(viewLifecycleOwner) {
            summaryBinding.apply {
                receiverAcctName.text = buildString {
                    append(it.accountFirstName)
                    append(" ")
                    append(it.accountLastName)
                }
                receiverAcctNo.text = it.accountNumber
            }
        }
        viewModel.remark.observe(viewLifecycleOwner) {
            summaryBinding.summaryRemark.text = it
        }

        viewModel.amount.observe(viewLifecycleOwner) {
            summaryBinding.summaryAmount.text = it
        }
    }

    private fun navigateToDashboard() {
        findNavController().navigate(Dashboard)
    }
}