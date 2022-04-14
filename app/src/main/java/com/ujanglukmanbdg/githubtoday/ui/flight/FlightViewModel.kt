package com.ujanglukmanbdg.githubtoday.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Your Flight Schedule is here"
    }
    val text: LiveData<String> = _text
}