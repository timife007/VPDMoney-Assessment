package com.timife.vpdmoneyassessment.presentation.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.vpdmoneyassessment.databinding.FragmentAccountsListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class AccountsFragment : Fragment() {

    private lateinit var accountsBinding: FragmentAccountsListBinding

    private val viewModel: AccountsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        accountsBinding = FragmentAccountsListBinding.inflate(inflater, container, false)
        val recyclerView = accountsBinding.accountsRecyclerview

        viewModel.accounts.observe(viewLifecycleOwner) {
            if (it.loading) {
                accountsBinding.accountListProgress.visibility = View.VISIBLE
            } else if (it.data != null) {
                with(recyclerView) {
                    adapter = AccountsRecyclerViewAdapter(it.data)
                }

                accountsBinding.accountListProgress.visibility = View.GONE
            } else {
                accountsBinding.accountListProgress.visibility = View.GONE
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
            }
        }
        accountsBinding.backNavButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return accountsBinding.root
    }
}