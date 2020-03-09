package com.example.xpenses.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayInMonthAdapter
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayInMonthAdapter.DayPaymentsClickListener
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModel
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class MonthPaymentsFragment : BasePaymentsFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        setHasOptionsMenu(true)

        //build the adapter
        val adapter = createPaymentsOfDayInMonthAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter

        //create view model
        val viewModelFactory = MonthPaymentsFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(
            MonthPaymentsFragmentViewModel::class.java
        )

        //fetch the data to be represented on the ui
        viewModel.fetchDaysPayments().observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.fetchPaymentsDerivedInfo().observe(this, Observer { paymentsInfoCarouselAdapter.submitList(it) })

        return binding.root
    }

    private fun createPaymentsOfDayInMonthAdapter(): PaymentsOfDayInMonthAdapter {
        return PaymentsOfDayInMonthAdapter(object : DayPaymentsClickListener {
            override fun onDayPaymentsClick(dayDateString:String) {
                findNavController().navigate(MonthPaymentsFragmentDirections.actionMonthPaymentsFragment2ToSpecificDayPaymentsFragment(dayDateString))
            }
        })
    }
}
