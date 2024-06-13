package com.timife.vpdmoneyassessment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.timife.vpdmoneyassessment.presentation.accounts.AccountsFragment
import com.timife.vpdmoneyassessment.presentation.dashboard.DashboardFragment
import com.timife.vpdmoneyassessment.presentation.login.LoginFragment
import com.timife.vpdmoneyassessment.presentation.summary.TransactionSummaryFragment
import com.timife.vpdmoneyassessment.presentation.transfer.TransferFragment
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Dashboard

@Serializable
object Transfer

@Serializable
object TransactionSummary

@Serializable
object AccountManagement

fun navigationGraph(navController: NavController): NavGraph {
    return navController.createGraph(startDestination = Login) {
        fragment<LoginFragment, Login> {
        }

        fragment<DashboardFragment, Dashboard> {
        }

        fragment<AccountsFragment, AccountManagement> {
        }

        fragment<TransferFragment, Transfer> {
        }

        fragment<TransactionSummaryFragment, TransactionSummary> {
        }
    }
}




