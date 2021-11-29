package com.pedraza.sebastian.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pedraza.sebastian.news.domain.repository.NewsRepo
import com.pedraza.sebastian.news.domain.usecase.GetNewsHeadlinesUseCase
import com.pedraza.sebastian.news.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 *  para pasar el contexto usar android viewmodel
 */
class NewsViewModel(private val app: Application, private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase) : AndroidViewModel(app) {

    fun getTopHeadlines(
        country: String,
        page: Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = getNewsHeadlinesUseCase.execute(country, page)
            emit(response)
        } catch (error: Exception) {
            emit(Resource.Error(error.message.toString()))
        }
    }

}