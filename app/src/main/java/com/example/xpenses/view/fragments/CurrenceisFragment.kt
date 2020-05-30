package com.example.xpenses.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.xpenses.repository.CurrencyRepository
import androidx.databinding.DataBindingUtil

import com.example.xpenses.R
import com.example.xpenses.databinding.FragmentCurrenceisBinding
import com.example.xpenses.view.recycler_view.adapters.CurrencyRateAdapter
import com.example.xpenses.view_model.CurrencyFragmentViewModel
import com.example.xpenses.view_model.view_models_factories.CurrencyFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class CurrenceisFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //create view model
        val binding = DataBindingUtil.inflate<FragmentCurrenceisBinding>(
            inflater,
            R.layout.fragment_currenceis, container, false
        )
        val adapter = CurrencyRateAdapter()
        binding.currenciesRateRecyclerView.adapter = adapter
        val viewModelFactory = CurrencyFragmentViewModelFactory(CurrencyRepository())
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(
            CurrencyFragmentViewModel::class.java)
        viewModel.fetchCurrenciesRate()
        viewModel.currenciesRate.observe(this, Observer { adapter.submitList(it) })
        return binding.root
    }
}
