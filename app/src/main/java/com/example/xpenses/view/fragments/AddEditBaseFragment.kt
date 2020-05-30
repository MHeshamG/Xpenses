package com.example.xpenses.view.fragments


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.xpenses.R
import com.example.xpenses.repository.RepositoryInterface
import com.example.xpenses.databinding.FragmentAddEditPaymentBinding
import com.example.xpenses.view.dialogs.PaymentTypeIconsDialog
import com.example.xpenses.view.recycler_view.adapters.PaymentTypeIconsRecyclerAdapter
import com.xpenses.model.PaymentType
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
open class AddEditBaseFragment : Fragment() {

    var choosenPaymentType: PaymentType = PaymentType.FOOD;
    lateinit var binding:FragmentAddEditPaymentBinding
    lateinit var application:Application
    val dataSource: RepositoryInterface by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentAddEditPaymentBinding>(
            inflater,
            R.layout.fragment_add_edit_payment,
            container,
            false
        )
         application = requireNotNull(this.activity).application

        binding.chooseTypeButton.setOnClickListener {
            val choosePaymentTypeDialog =
                PaymentTypeIconsDialog(this.requireActivity())
            choosePaymentTypeDialog.adapter =
                PaymentTypeIconsRecyclerAdapter(
                    object :
                        PaymentTypeIconsRecyclerAdapter.OnPaymentTypeItemClickListener {
                        override fun onPaymentItemClick(iconType: Pair<PaymentType, Int>) {
                            choosePaymentTypeDialog.dismiss()
                            choosenPaymentType = iconType.first
                            binding.paymentTypeText.text = iconType.first.name
                            binding.iconTypeImageView.setImageResource(iconType.second)
                        }
                    })
            choosePaymentTypeDialog.show()
        }
        return binding.root;
    }
}
