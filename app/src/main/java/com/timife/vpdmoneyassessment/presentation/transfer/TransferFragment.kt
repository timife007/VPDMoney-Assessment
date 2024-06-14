package com.timife.vpdmoneyassessment.presentation.transfer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.data.mocks.mockAccounts
import com.timife.vpdmoneyassessment.databinding.FragmentTransferBinding
import com.timife.vpdmoneyassessment.navigation.TransactionSummary
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TransferFragment : Fragment() {
    private lateinit var transferBinding: FragmentTransferBinding

    private val viewModel: TransferViewModel by viewModels()

    private lateinit var senderArrayAdapter: ArrayAdapter<String>
    private lateinit var receiverArrayAdapter: ArrayAdapter<String>
    private var acctBalance: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        transferBinding = FragmentTransferBinding.inflate(inflater, container, false)
        senderArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            mockAccounts.map {
                it.accountNumber
            })
        receiverArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            mockAccounts.map {
                it.accountNumber
            }
        )

        viewModel.accountDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                transferBinding.balanceTextIndicator.text = buildString {
                    append("Bal:")
                    append(" ")
                    append("₦")
                    append(it.accountBalance)
                }
                acctBalance = it.accountBalance
            }
        }


        setupAutoCompleteText(transferBinding.srcAcctTextInput, senderArrayAdapter, true)
        setupAutoCompleteText(transferBinding.acctNoTextInput, receiverArrayAdapter)

        transferBinding.continueButton.setOnClickListener {
            if (validateInputs()) {
                navigateToSummary()
            }
        }
        return transferBinding.root
    }

    private fun navigateToSummary() {
        transferBinding.apply {
            findNavController().navigate(
                TransactionSummary(
                    srcAcctTextInput.text.toString(),
                    acctNoTextInput.text.toString(),
                    amountTextInput.text.toString().toInt(),
                    remarkTextInput.text.toString()
                )
            )
        }

    }

    //validate all transfer details
    private fun validateInputs(): Boolean {
        transferBinding.apply {
            if (!viewModel.isAcctNoValid(acctNoTextInput.text.toString())) {
                acctNoInputLayout.error = "Account does not exist"
                return false
            } else if (!viewModel.isAcctNoValid(srcAcctTextInput.text.toString())) {
                acctNoInputLayout.error = "Account does not exist"
                return false
            } else if (amountTextInput.text.toString().toInt() > acctBalance) {
                transferBinding.amountTextLayout.error = "Insufficient funds"
                return false
            }
        }
        return true
    }


    //autocomplete setup for sender/receiver's account
    private fun setupAutoCompleteText(
        autoComplete: AutoCompleteTextView,
        adapter: ArrayAdapter<String>,
        isSender: Boolean = false
    ) {

        autoComplete.apply {
            setAdapter(adapter)
            threshold = 1
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Timber.tag("BEFORE_TEXT: ").d(s.toString())
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fetchAcctSuggestions(s.toString(), adapter)
                }

                override fun afterTextChanged(s: Editable?) {
                    Timber.tag("AFTER TEXT: ").d(s.toString())
                }
            })

            setOnItemClickListener { parent, view, position, id ->
                if (isSender) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    viewModel.fetchAccountDetails(selectedItem)
                }
            }
        }
    }


    private fun fetchAcctSuggestions(query: String, adapter: ArrayAdapter<String>) {
        val result = mockAccounts.filter {
            it.accountNumber.contains(query)
        }
        adapter.clear()
        adapter.addAll(result.map { it.accountNumber })
        adapter.notifyDataSetChanged()
    }
}