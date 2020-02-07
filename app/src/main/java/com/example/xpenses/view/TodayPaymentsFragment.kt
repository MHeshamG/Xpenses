package com.example.xpenses.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentTodayPaymentsBinding
import com.example.xpenses.view.recycler_view.RecyclerAdapter
import com.example.xpenses.view.recycler_view.RecyclerAdapter.OnPaymentItemClickListener
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModelFactory
import com.xpenses.room.PaymentsDatabase

/**
 * A simple [Fragment] subclass.
 */
class TodayPaymentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adapter = createAdapter()
        val binding = DataBindingUtil.inflate<FragmentTodayPaymentsBinding>(inflater,R.layout.fragment_today_payments, container, false)
        setHasOptionsMenu(true)
        binding.todayPaymentsRecyclerView.adapter = adapter
        val application = requireNotNull(this.activity).application
        val dataSource = PaymentsDatabase.getDatabase(application).paymentDao
        val viewModelFactory = TodayPaymentsFragmentViewModelFactory(dataSource,application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(TodayPaymentsFragmentViewModel::class.java)
        viewModel.todayPayments.observe(this, Observer { it?.let { adapter.submitList(it) } })
        return binding.root
    }

    private fun createAdapter(): RecyclerAdapter {
        return RecyclerAdapter(object :
            OnPaymentItemClickListener {
            override fun onPaymentItemClick(paymentId: Long) {
                findNavController()
                    .navigate(
                        TodayPaymentsFragmentDirections.actionTodayPaymentsFragmentToAddEditPaymentFragment(
                            paymentId
                        )
                    )
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())||super.onOptionsItemSelected(item)
    }
}
