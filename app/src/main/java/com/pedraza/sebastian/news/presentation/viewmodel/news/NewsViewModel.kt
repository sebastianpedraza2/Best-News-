package com.pedraza.sebastian.news.presentation.viewmodel.news

import android.app.Application
import androidx.lifecycle.*
import com.pedraza.sebastian.news.data.model.ApiResponse
import com.pedraza.sebastian.news.domain.usecase.GetNewsHeadlinesUseCase
import com.pedraza.sebastian.news.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *  para pasar el contexto usar android viewmodel
 */
class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : AndroidViewModel(app) {


    private var newsHeadlines = MutableLiveData<Resource<ApiResponse>>()
    fun getNewsHeadlines(): LiveData<Resource<ApiResponse>> {
        return newsHeadlines
    }

    fun getTopHeadlines(
        country: String,
        page: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadlines.postValue(Resource.Loading())
        try {
            val response = getNewsHeadlinesUseCase.execute(country, page)
            newsHeadlines.postValue(response)

        } catch (error: Exception) {
            newsHeadlines.postValue(Resource.Error(error.message.toString()))

        }
    }

}