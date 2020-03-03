package com.example.xpenses.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.xpenses.databinding.FragmentAddEditPaymentBinding
import com.example.xpenses.view_model.AddPaymentFragmentViewModel
import com.example.xpenses.view_model.AddPaymentFragmentViewModelFactory
import com.example.xpenses.model.Payment

/**
 * A simple [Fragment] subclass.
 */
class AddPaymentFragment : AddEditBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModelFactory = AddPaymentFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddPaymentFragmentViewModel::class.java)
        binding.saveButton.setOnClickListener {
            onSaveButtonClicked(viewModel)
        }
        return binding.root
    }

    private fun onSaveButtonClicked(viewModel: AddPaymentFragmentViewModel) {
        savePayment(binding, viewModel)
        navigateToTodayPaymentsFragment()
    }

    private fun savePayment(
        binding: FragmentAddEditPaymentBinding,
        viewModel: AddPaymentFragmentViewModel
    ) {
        var cost = 0.0
        var description = ""
        binding.paymentCost.editText?.let { cost = it.text.toString().toDouble() }
        binding.paymentDescription.editText?.let { description = it.text.toString() }
        viewModel.onSavePayment(
            Payment(
                cost = cost,
                description = description,
                type = choosenPaymentType.typeInt
            )
        )
    }

    private fun navigateToTodayPaymentsFragment() {
        findNavController().navigate(AddPaymentFragmentDirections.actionAddPaymentFragmentToTodayPaymentsFragment())
    }

}
