package com.timife.vpdmoneyassessment.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timife.vpdmoneyassessment.R
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
            holder.transactionStatus.text = buildString {

                when(item.transactionType){
                    TransactionType.Transfer ->{
                        append("Transfer to ")
                        append(item.userName + " ")
                    }
                    TransactionType.Recharge ->{
                        append("Airtime Recharge ")
                    }
                    TransactionType.Electricity ->{
                        append("Electricity Payment ")
                    }
                }
                append(item.transactionStatus.name)
            }
            holder.historyDate.text = item.date
            holder.time.text = item.time
            holder.moneyInvolved.text = item.amount

            holder.icon.apply {
                when(item.transactionStatus){
                    TransactionStatus.SUCCESSFUL -> {
                        holder.icon.setImageResource(R.drawable.ic_sent)
                    }
                    TransactionStatus.FAILED -> {
                        holder.icon.setImageResource(R.drawable.ic_failed)
                    }
                    TransactionStatus.PENDING -> {
                        holder.icon.setImageResource(R.drawable.ic_pending)
                    }
                }
            }
        }

        override fun getItemCount(): Int = values.size

        inner class ViewHolder(binding: TransactHistoryListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val transactionStatus: TextView = binding.transactionHistoryDetail
            val historyDate: TextView = binding.transactionDate
            val moneyInvolved: TextView = binding.moneyInvolved
            val time: TextView = binding.historyTime
            val icon = binding.transactionIcon

            override fun toString(): String {
                return super.toString() + " '" + transactionStatus.text + "'"
            }
        }

    }