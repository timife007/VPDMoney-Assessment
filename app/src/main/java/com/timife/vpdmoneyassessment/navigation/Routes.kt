package com.timife.vpdmoneyassessment.navigation

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.timife.vpdmoneyassessment.presentation.accounts.AccountsFragment
import com.timife.vpdmoneyassessment.presentation.auth.login.LoginFragment
import com.timife.vpdmoneyassessment.presentation.auth.signup.SignupFragment
import com.timife.vpdmoneyassessment.presentation.dashboard.DashboardFragment
import com.timife.vpdmoneyassessment.presentation.summary.TransactionSummaryFragment
import com.timife.vpdmoneyassessment.presentation.transfer.TransferFragment
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Dashboard

@Serializable
object Transfer

@Serializable
@Parcelize
data class TransactionSummary(
    val senderAcct: String,
    val receiverAcct: String,
    val amount: Int,
    val remark: String = ""
) : Parcelable

@Serializable
object AccountManagement

@Serializable
object Signup

fun navigationGraph(navController: NavController): NavGraph {
    return navController.createGraph(startDestination = Login) {
        fragment<LoginFragment, Login> {
            label = "Login"
        }

        fragment<DashboardFragment, Dashboard> {
            label = "Dashboard"
        }

        fragment<AccountsFragment, AccountManagement> {
            label = "AccountManagement"
        }

        fragment<TransferFragment, Transfer> {
            label = "Transfer"
        }

        fragment<TransactionSummaryFragment, TransactionSummary> {
            label = "TransactionSummary"
        }

        fragment<SignupFragment, Signup> {
            label = "Signup"
        }
    }
}




