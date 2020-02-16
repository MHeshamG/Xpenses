package com.example.xpenses.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentEditPaymentBinding
import com.example.xpenses.view_model.EditPaymentFragmentViewModel
import com.example.xpenses.view_model.EditPaymentFragmentViewModelFactory
import com.xpenses.model.LeafPayment
import com.xpenses.room.PaymentsDatabase

/**
 * A simple [Fragment] subclass.
 */
class EditPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentEditPaymentBinding>(
            inflater,
            R.layout.fragment_edit_payment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = PaymentsDatabase.getDatabase(application).paymentDao
        val args = EditPaymentFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory = EditPaymentFragmentViewModelFactory(dataSource, args.paymentId, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(EditPaymentFragmentViewModel::class.java)
        viewModel.thisPayment.observe(this, Observer {it?.let { loadArgsToView(binding, it as LeafPayment) } })
        binding.saveButton.setOnClickListener {
            updatePayment(binding, viewModel, viewModel.thisPayment.value!!)
        }
        binding.deleteButton.setOnClickListener {
            viewModel.onDeletePayment(viewModel.thisPayment.value!!)
        }
        return binding.root
    }

    private fun loadArgsToView(binding: FragmentEditPaymentBinding, leafPayment: LeafPayment) {
        binding.paymentCost.editText?.setText(leafPayment.cost.toString())
        binding.paymentDescription.editText?.setText(leafPayment.description.toString())
    }

    private fun updatePayment(
        binding: FragmentEditPaymentBinding,
        viewModel: EditPaymentFragmentViewModel, leafPayment: LeafPayment
    ) {
        var cost = 0.0
        var description = ""
        binding.paymentCost.editText?.let { cost = it.text.toString().toDouble() }
        binding.paymentDescription.editText?.let { description = it.text.toString() }
        leafPayment.cost = cost
        leafPayment.description = description
        viewModel.onUpdatePayment(leafPayment)
    }


}


