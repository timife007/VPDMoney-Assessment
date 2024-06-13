package com.timife.vpdmoneyassessment.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.timife.vpdmoneyassessment.MainActivity
import com.timife.vpdmoneyassessment.R

class DashboardFragment : Fragment() {

    private val columnCount = 1

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.activity as MainActivity).apply {
            this.findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.VISIBLE
        }
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.dashboard_recycler_view)


        // Set the adapter
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                adapter = DashboardHistoryAdapter(mockTransaction)
            }
        }
        return view
    }
}