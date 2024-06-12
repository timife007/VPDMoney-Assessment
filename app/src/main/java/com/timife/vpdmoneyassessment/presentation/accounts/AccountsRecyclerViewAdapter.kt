package com.timife.vpdmoneyassessment.presentation.accounts

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

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
        holder.accountName.text = item.accountName
        holder.accountNumber.text = item.accountNumber
        holder.accountBalance.text = item.accountBalance
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAccountsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val accountName: TextView = binding.accountName
        val accountNumber: TextView = binding.accountNumber
        val accountBalance: TextView = binding.accountBalance

        override fun toString(): String {
            return super.toString() + " '" + accountNumber.text + "'"
        }
    }

}