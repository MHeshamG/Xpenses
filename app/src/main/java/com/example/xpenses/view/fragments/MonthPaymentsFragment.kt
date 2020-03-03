package com.example.xpenses.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayInMonthAdapter
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModel
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class MonthPaymentsFragment : BasePaymentsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val adapter = PaymentsOfDayInMonthAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter
        setHasOptionsMenu(true)
        val viewModelFactory = MonthPaymentsFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(
            MonthPaymentsFragmentViewModel::class.java
        )
        viewModel.getDaysPayments().observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.getPaymentsInfo()
            .observe(this, Observer { paymentsInfoCarouselAdapter.submitList(it) })
        return binding.root
    }
}
