package com.timife.vpdmoneyassessment.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timife.vpdmoneyassessment.data.mocks.Transaction
import com.timife.vpdmoneyassessment.databinding.TransactHistoryListItemBinding

class DashboardHistoryAdapter (
    private val values: List<Transaction>
    ) : RecyclerView.Adapter<DashboardHistoryAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                TransactHistoryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.transactionDetail.text = buildString {
                append(" Transfer from ")
                append(item.sender)
                append(" to ")
                append(item.receiver)
            }
            holder.historyDate.text = item.date
            holder.time.text = item.time
            holder.moneyInvolved.text = buildString {
                append("₦")
                append(" ")
                append(item.amount?.toDouble()?.toInt())
            }
        }

        override fun getItemCount(): Int = values.size

        inner class ViewHolder(binding: TransactHistoryListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val transactionDetail: TextView = binding.transactionHistoryDetail
            val historyDate: TextView = binding.transactionDate
            val moneyInvolved: TextView = binding.moneyInvolved
            val time: TextView = binding.historyTime
            val icon = binding.transactionIcon

        }

    }