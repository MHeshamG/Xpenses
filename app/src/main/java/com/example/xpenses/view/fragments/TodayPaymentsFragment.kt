package com.example.xpenses.view.fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.xpenses.R
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayAdapter
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class TodayPaymentsFragment : BasePaymentsFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        setHasOptionsMenu(true)

        //build adapter
        val adapter = createAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter

        //create view model
        val viewModelFactory = TodayPaymentsFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayPaymentsFragmentViewModel::class.java)

        //fetch the data to be represented on the ui
        viewModel.todayPayments.observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.getPaymentsInfo().observe(this, Observer { paymentsInfoCarouselAdapter.submitList(it) })


        return binding.root
    }

    private fun createAdapter():PaymentsOfDayAdapter{
        return PaymentsOfDayAdapter(object :
            PaymentsOfDayAdapter.OnPaymentItemClickListener {
            override fun onPaymentItemClick(paymentId: Long) {
                findNavController().navigate(TodayPaymentsFragmentDirections.actionTodayPaymentsFragmentToEditPaymentFragment(paymentId))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
