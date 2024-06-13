package com.timife.vpdmoneyassessment.presentation.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timife.vpdmoneyassessment.data.mocks.mockAccounts
import com.timife.vpdmoneyassessment.databinding.FragmentAccountsListBinding

/**
 * A fragment representing a list of Items.
 */
class AccountsFragment : Fragment() {

    private lateinit var accountsBinding: FragmentAccountsListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountsBinding = FragmentAccountsListBinding.inflate(inflater, container, false)
        val recyclerView = accountsBinding.accountsRecyclerview

        // Set the adapter
        with(recyclerView) {
            adapter = AccountsRecyclerViewAdapter(mockAccounts)
        }
        return accountsBinding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}