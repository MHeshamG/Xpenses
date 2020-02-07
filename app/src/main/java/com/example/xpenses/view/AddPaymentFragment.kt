package com.example.xpenses.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentAddEditPaymentBinding
import com.example.xpenses.view_model.AddPaymentFragmentViewModel
import com.example.xpenses.view_model.AddPaymentFragmentViewModelFactory
import com.xpenses.room.PaymentsDatabase
import com.xwallet.business.LeafPayment
import com.xwallet.business.PaymentType

/**
 * A simple [Fragment] subclass.
 */
class AddPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAddEditPaymentBinding>(
            inflater,
            R.layout.fragment_add_payment,
            container,
            false
        )
        val argsBundle = arguments
        if (argsBundle != null) {
            val args = AddEditPaymentFragmentArgs.fromBundle(argsBundle)
            loadArgsToView(args.paymentId)
        }
        val application = requireNotNull(this.activity).application
        val dataSource = PaymentsDatabase.getDatabase(application).paymentDao
        val viewModelFactory = AddPaymentFragmentViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddPaymentFragmentViewModel::class.java)
        binding.saveButton.setOnClickListener {
            savePayment(binding, viewModel)
            navigateToTodayPaymentsFragment()
        }
        return binding.root;
    }

    private fun navigateToTodayPaymentsFragment() {
        findNavController().navigate(AddEditPaymentFragmentDirections.actionAddEditPaymentFragmentToTodayPaymentsFragment())
    }

    private fun savePayment(
        binding: FragmentAddEditPaymentBinding,
        viewModel: AddPaymentFragmentViewModel
    ) {
        var cost = 0.0
        var description = ""
        binding.paymentCost.editText?.let { cost = it?.text.toString().toDouble() }
        binding.paymentDescription.editText.let { description = it?.text.toString() }
        viewModel.onSavePayment(LeafPayment(cost = cost, description = description, type = PaymentType.FOOD.typeInt))
    }

    private fun loadArgsToView(paymentId: Long) {
    }
}
