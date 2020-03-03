package com.example.xpenses.view.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentBasePaymentsBinding
import com.example.xpenses.view.recycler_view.adapters.PaymentsInfoCarouselAdapter
import com.example.xpenses.view.recycler_view.decorations.DotIndicatorDecoration
import com.example.xpenses.room.PaymentDao
import com.example.xpenses.room.PaymentsDatabase

abstract class BasePaymentsFragment : Fragment() {
    protected lateinit var paymentsInfoCarouselAdapter: PaymentsInfoCarouselAdapter
    protected lateinit var binding: FragmentBasePaymentsBinding
    protected lateinit var application: Application
    protected lateinit var dataSource: PaymentDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        application = requireNotNull(this.activity).application
        dataSource = PaymentsDatabase.getDatabase(application).paymentDao
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_base_payments, container, false
        )
        paymentsInfoCarouselAdapter = PaymentsInfoCarouselAdapter()
        binding.paymentsInfoRecyclerView.setupPaymentsInfoRecyclerView(paymentsInfoCarouselAdapter)

        return binding.root
    }

    private fun RecyclerView.setupPaymentsInfoRecyclerView(paymentsInfoCarouselAdapter: PaymentsInfoCarouselAdapter) {
        PagerSnapHelper().attachToRecyclerView(this)
        this.adapter = paymentsInfoCarouselAdapter
        addItemDecoration(
            DotIndicatorDecoration(
                context
            )
        )
    }
}