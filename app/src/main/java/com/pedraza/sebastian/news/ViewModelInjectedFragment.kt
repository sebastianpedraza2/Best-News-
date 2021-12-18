package com.pedraza.sebastian.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pedraza.sebastian.news.presentation.viewmodel.constructorInjectedViewmodel.HiltViewModel

class ViewModelInjectedFragment : Fragment(R.layout.fragment_view_model_injected) {

    /**
     * Obteniendo el viewmodel injectado con hilt
     */
    private val viewModel: HiltViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}