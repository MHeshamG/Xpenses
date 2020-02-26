package com.example.xpenses.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentTodayPaymentsBinding
import com.example.xpenses.view.recycler_view.adapters.CarouselAdapter
import com.example.xpenses.view.recycler_view.adapters.RecyclerAdapter
import com.example.xpenses.view.recycler_view.decorations.DotIndicatorDecoration

open class BasePaymentsFragment: Fragment() {
    protected lateinit var adapter: RecyclerAdapter
    protected lateinit var carouselAdapter: CarouselAdapter
    protected lateinit var binding: FragmentTodayPaymentsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = createAdapter()
        carouselAdapter = createCarouselAdapter()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_today_payments, container, false)
        binding.todayPaymentsRecyclerView.adapter = adapter
        binding.paymentsInfoRecyclerView.run {
            setupPaymentsInfoRecyclerView(carouselAdapter)
        }
        return binding.root
    }

    private fun RecyclerView.setupPaymentsInfoRecyclerView(carouselAdapter: CarouselAdapter) {
        PagerSnapHelper().attachToRecyclerView(this)
        this.adapter = carouselAdapter
        addItemDecoration(
            DotIndicatorDecoration(
                context
            )
        )
    }

    private fun createCarouselAdapter(): CarouselAdapter {
        return CarouselAdapter()
    }

    private fun createAdapter(): RecyclerAdapter {
        return RecyclerAdapter(object :
            RecyclerAdapter.OnPaymentItemClickListener {
            override fun onPaymentItemClick(paymentId: Long) {
                findNavController()
                    .navigate(
                        TodayPaymentsFragmentDirections.actionTodayPaymentsFragmentToEditPaymentFragment(
                            paymentId
                        )
                    )
            }
        })
    }

}