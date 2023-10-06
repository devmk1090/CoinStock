package com.devkproject.coinstock.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.coinstock.Repository.SocketRepository
import com.devkproject.coinstock.model.Upbit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    socketRepository: SocketRepository
) : ViewModel() {

    private val _coin = MutableLiveData<List<Upbit>>()
    val coin: LiveData<List<Upbit>> = _coin

    init {
        socketRepository.refreshCoin(viewModelScope)
    }
}