package com.example.xpenses.view.fragments


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentAddEditPaymentBinding
import com.example.xpenses.model.Payment
import com.example.xpenses.model.PaymentTypeIconResourceMap
import com.example.xpenses.view_model.EditPaymentFragmentViewModel
import com.example.xpenses.view_model.EditPaymentFragmentViewModelFactory
import com.xpenses.model.PaymentType

/**
 * A simple [Fragment] subclass.
 */
class EditPaymentFragment : AddEditBaseFragment() {

    lateinit var viewModel: EditPaymentFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddEditPaymentBinding>(inflater, R.layout.fragment_add_edit_payment, container, false)

        setHasOptionsMenu(true)

        val args = EditPaymentFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = EditPaymentFragmentViewModelFactory(dataSource, args.paymentId, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditPaymentFragmentViewModel::class.java)
        viewModel.thisPayment.observe(this, Observer { it?.let { loadArgsToView(binding, it) } })

        binding.saveButton.setOnClickListener {
            updatePayment(binding, viewModel, viewModel.thisPayment.value!!)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_payment_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_menu_item -> {
                viewModel.onDeletePayment()
                navigateToTodayPaymentsFragment()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadArgsToView(binding: FragmentAddEditPaymentBinding, payment: Payment) {
        binding.iconTypeImageView.setImageResource(PaymentTypeIconResourceMap[PaymentType.fromInt(payment.type)]!!)
        binding.paymentTypeText.text = PaymentType.fromInt(payment.type)?.name
        binding.paymentCost.editText?.setText(payment.cost.toString())
        binding.paymentDescription.editText?.setText(payment.description)
    }

    private fun updatePayment(
        binding: FragmentAddEditPaymentBinding,
        viewModel: EditPaymentFragmentViewModel, payment: Payment
    ) {
        var cost = 0.0
        var description = ""
        binding.paymentCost.editText?.let { cost = it.text.toString().toDouble() }
        binding.paymentDescription.editText?.let { description = it.text.toString() }
        payment.cost = cost
        payment.description = description
        viewModel.onUpdatePayment(payment)
    }

    private fun navigateToTodayPaymentsFragment() {
        findNavController().navigate(EditPaymentFragmentDirections.actionEditPaymentFragmentToTodayPaymentsFragment())
    }


}


