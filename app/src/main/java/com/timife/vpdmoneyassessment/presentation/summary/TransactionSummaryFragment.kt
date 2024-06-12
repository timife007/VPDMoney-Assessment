package com.timife.vpdmoneyassessment.presentation.summary

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timife.vpdmoneyassessment.R

class TransactionSummaryFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionSummaryFragment()
    }

    private val viewModel: TransactionSummaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_transaction_summary, container, false)
    }
}