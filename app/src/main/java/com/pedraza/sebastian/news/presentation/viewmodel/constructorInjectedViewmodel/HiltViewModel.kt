package com.pedraza.sebastian.news.presentation.viewmodel.constructorInjectedViewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pedraza.sebastian.news.domain.usecase.GetNewsHeadlinesUseCase

class HiltViewModel @ViewModelInject constructor(private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase) :
    ViewModel() {

    init {

        Log.d("HiltViewmodel", "View model initialized")
    }
}