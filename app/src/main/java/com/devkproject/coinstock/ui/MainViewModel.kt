package com.devkproject.coinstock.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.coinstock.model.TickerList
import com.devkproject.coinstock.repository.CoinRepository
import com.devkproject.coinstock.model.Upbit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _coin = MutableLiveData<List<Upbit>>()
    val coin: LiveData<List<Upbit>> = _coin

    init {
        coinRepository.refreshCoin()
        upbitTickerList()
    }

    private val _responseUpbitTickerList = MutableLiveData<List<TickerList>>()
    val responseUpbitTickerList: LiveData<List<TickerList>> = _responseUpbitTickerList
    private fun upbitTickerList() {
        viewModelScope.launch {
            val response = coinRepository.getUpbitTickerList()
            _responseUpbitTickerList.value = response
        }
    }
}