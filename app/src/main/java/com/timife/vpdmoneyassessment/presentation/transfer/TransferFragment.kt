package com.timife.vpdmoneyassessment.presentation.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentTransferBinding
import com.timife.vpdmoneyassessment.navigation.TransactionSummary

class TransferFragment : Fragment() {
    private lateinit var transferBinding: FragmentTransferBinding

    companion object {
        fun newInstance() = TransferFragment()
    }

    private val viewModel: TransferViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        transferBinding = FragmentTransferBinding.inflate(inflater, container, false)
        transferBinding.continueButton.setOnClickListener { navigateToSummary() }
        return transferBinding.root
    }

    private fun navigateToSummary() {
        findNavController().navigate(TransactionSummary)
    }
}