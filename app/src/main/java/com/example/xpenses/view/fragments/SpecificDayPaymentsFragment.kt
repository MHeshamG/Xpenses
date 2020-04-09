package com.example.xpenses.view.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.xpenses.R
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayAdapter
import com.example.xpenses.view_model.SpecificDayPaymentsFragmentViewModel
import com.example.xpenses.view_model.view_models_factories.SpecificPaymentsFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SpecificDayPaymentsFragment : BasePaymentsFragment() {

    private lateinit var dayDateString: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        setHasOptionsMenu(true)

        val args = SpecificDayPaymentsFragmentArgs.fromBundle(arguments!!)
        dayDateString = args.dayDateString

        //build adapter
        val adapter = createAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter

        //create view model
        val viewModelFactory = SpecificPaymentsFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpecificDayPaymentsFragmentViewModel::class.java)

        //fetch the data to be represented on the ui
        viewModel.fetchSpecificDayPayments(args.dayDateString)
        viewModel.specificDayPayments.observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.specificDayPaymentsInfo.observe(this, Observer { paymentsInfoCarouselAdapter.paymentsDerivedInfolist = it })

        return binding.root
    }

    private fun createAdapter(): PaymentsOfDayAdapter {
        return PaymentsOfDayAdapter(object :
            PaymentsOfDayAdapter.OnPaymentItemClickListener {
            override fun onPaymentItemClick(paymentId: Long) {
                findNavController()
                    .navigate(
                        SpecificDayPaymentsFragmentDirections.actionSpecificDayPaymentsFragmentToEditPaymentFragment2(paymentId)
                    )
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addPaymentFragment -> {
                findNavController().navigate(
                    SpecificDayPaymentsFragmentDirections.actionSpecificDayPaymentsFragmentToAddPaymentFragment2(dayDateString)
                )
                return true
            }
        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}
