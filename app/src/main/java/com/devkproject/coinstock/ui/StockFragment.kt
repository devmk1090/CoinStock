package com.devkproject.coinstock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devkproject.coinstock.databinding.FragmentStockBinding

class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockBinding.inflate(layoutInflater)
        return binding.root
    }
}