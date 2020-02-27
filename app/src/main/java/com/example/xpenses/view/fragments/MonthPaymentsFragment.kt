package com.example.xpenses.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentTodayPaymentsBinding
import com.example.xpenses.databinding.MonthPaymentsLayoutBinding
import com.example.xpenses.view.recycler_view.adapters.CarouselAdapter
import com.example.xpenses.view.recycler_view.adapters.PaymentsOfDayAdapter
import com.example.xpenses.view.recycler_view.adapters.RecyclerAdapter
import com.example.xpenses.view.recycler_view.decorations.DotIndicatorDecoration
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModel
import com.example.xpenses.view_model.MonthPaymentsFragmentViewModelFactory
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModel
import com.example.xpenses.view_model.TodayPaymentsFragmentViewModelFactory
import com.xpenses.room.PaymentsDatabase

/**
 * A simple [Fragment] subclass.
 */
class MonthPaymentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<MonthPaymentsLayoutBinding>(inflater,
            R.layout.month_payments_layout, container, false)
        val adapter = PaymentsOfDayAdapter(object:RecyclerAdapter.OnPaymentItemClickListener{
            override fun onPaymentItemClick(paymentId: Long) {
            }
        })
        val carouselAdapter = createCarouselAdapter()
        binding.todayPaymentsRecyclerView.adapter = adapter
        binding.paymentsInfoRecyclerView.run {
            setupPaymentsInfoRecyclerView(carouselAdapter)
        }
        setHasOptionsMenu(true)
        val application = requireNotNull(this.activity).application
        val dataSource = PaymentsDatabase.getDatabase(application).paymentDao
        val viewModelFactory = MonthPaymentsFragmentViewModelFactory(dataSource,application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(
            MonthPaymentsFragmentViewModel::class.java)
        viewModel.getDaysPayments().observe(this, Observer { it?.let { adapter.submitList(it) } })
        viewModel.getPaymentsInfo().observe(this, Observer {  carouselAdapter.submitList(it)})
        return binding.root
    }

    private fun createCarouselAdapter(): CarouselAdapter {
        return CarouselAdapter()
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
}
