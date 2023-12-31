package com.devkproject.coinstock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devkproject.coinstock.databinding.FragmentCoinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinFragment : Fragment() {

    private lateinit var binding: FragmentCoinBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinBinding.inflate(layoutInflater)

        viewModel.coin.observe(viewLifecycleOwner) {

        }
        return binding.root
    }
}