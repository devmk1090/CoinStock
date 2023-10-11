package com.devkproject.coinstock.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devkproject.coinstock.repository.SocketRepository
import com.devkproject.coinstock.model.Upbit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val socketRepository: SocketRepository
) : ViewModel() {

    private val _coin = MutableLiveData<List<Upbit>>()
    val coin: LiveData<List<Upbit>> = _coin

    init {
        socketRepository.refreshCoin()
    }
}