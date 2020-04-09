package com.example.xpenses.view.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.xpenses.R
import com.example.xpenses.databinding.BudgetEntryDialogLayoutBinding
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayAdapter
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.example.xpenses.view_model.view_models_factories.TodayPaymentsFragmentViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class TodayPaymentsFragment : BasePaymentsFragment() {

    lateinit var budgetDialog: AlertDialog.Builder
    var budget: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        setHasOptionsMenu(true)

        //build adapter
        val adapter = createAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter

        //create view model
        val viewModelFactory = TodayPaymentsFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TodayPaymentsFragmentViewModel::class.java)

        budgetDialog = buildAlertDialog(inflater, container, viewModel)

        //fetch the data to be represented on the ui
        viewModel.todayPayments.observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.todayPaymentsInfo.observe(this, Observer {
            paymentsInfoCarouselAdapter.paymentsDerivedInfolist = it
            Log.d("xxx","called")
        })

        return binding.root
    }

    private fun createAdapter(): PaymentsOfDayAdapter {
        return PaymentsOfDayAdapter(object :
            PaymentsOfDayAdapter.OnPaymentItemClickListener {
            override fun onPaymentItemClick(paymentId: Long) {
                findNavController().navigate(
                    TodayPaymentsFragmentDirections.actionTodayPaymentsFragmentToEditPaymentFragment(
                        paymentId
                    )
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
            R.id.set_budget_menu_item -> {
                budgetDialog.show()
                return true
            }
        }
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun buildAlertDialog(
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewModel: TodayPaymentsFragmentViewModel
    ): AlertDialog.Builder {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Today Budget")


        val binding: BudgetEntryDialogLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.budget_entry_dialog_layout, container, false)
        builder.setView(binding.root)

        builder.setPositiveButton("OK") { dialog, which ->
            run {
                budget = binding.budgetEntryField.editText?.text.toString().toDouble()
                viewModel.addTodayBudget(budget)
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

        return builder
    }
}
