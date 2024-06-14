package com.timife.vpdmoneyassessment.presentation.dashboard

import androidx.lifecycle.ViewModel
import com.timife.vpdmoneyassessment.domain.repositories.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}