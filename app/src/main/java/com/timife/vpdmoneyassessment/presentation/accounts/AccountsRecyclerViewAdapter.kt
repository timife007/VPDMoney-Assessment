package com.timife.vpdmoneyassessment.presentation.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timife.vpdmoneyassessment.data.mockdata.Account
import com.timife.vpdmoneyassessment.databinding.FragmentAccountsItemBinding

class AccountsRecyclerViewAdapter(
    private val values: List<Account>
) : RecyclerView.Adapter<AccountsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAccountsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.accountName.text = buildString {
            append(item.accountFirstName)
            append(" ")
            append(item.accountLastName)
        }
        holder.accountNumber.text = buildString {
            append(item.bank)
            append("  ○  ")
            append(item.accountNumber)
        }
        holder.accountBalance.text = buildString {
            append("₦")
            append(item.accountBalance)

        }
        holder.initials.text = buildString {
            append(item.accountFirstName[0])
            append(" ")
            append(item.accountLastName[0])
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAccountsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val accountName: TextView = binding.accountName
        val accountNumber: TextView = binding.accountNumber
        val accountBalance: TextView = binding.accountBalance
        val initials: TextView = binding.initials
    }

}