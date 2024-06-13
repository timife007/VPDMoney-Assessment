package com.timife.vpdmoneyassessment.presentation.transfer

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.R
import com.timife.vpdmoneyassessment.navigation.Dashboard
import com.timife.vpdmoneyassessment.navigation.TransactionSummary

class TransferFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_transfer, container, false)
    }

    private fun navigateToSummary() {
        findNavController().navigate(TransactionSummary)
    }
}