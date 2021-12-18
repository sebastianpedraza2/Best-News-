package com.pedraza.sebastian.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.pedraza.sebastian.news.data.model.Article
import com.pedraza.sebastian.news.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding

    private val args: InfoFragmentArgs by navArgs()

    private lateinit var article: Article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)
        article = args.selectedArticle

        displayWebView()

    }

    private fun displayWebView() {
        binding.wvNews.apply {
            webViewClient = WebViewClient()
            if (article.url != "") {
                loadUrl(article.url)
            }

        }
    }

}