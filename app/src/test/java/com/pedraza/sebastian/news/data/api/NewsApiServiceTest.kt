package com.pedraza.sebastian.news.data.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class NewsApiServiceTest {

    private lateinit var newsApiService: NewsApiService
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {

        /**
         * Initializing the mockwebserver
         */
        mockWebServer = MockWebServer()
        /**
         * creating the service with retrofit, en base url  uso el del mock y lo dejo en vacio para que use el default
         */

        newsApiService = Retrofit.Builder().baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsApiService::class.java)

    }

    @After
    fun tearDown() {
        /**
         * cierro el server al terminar
         */
        mockWebServer.shutdown()
    }

    /**
     * Como no podemos acceder al contenid del archivo json que creamos desde aca hay que leerlo
     */

    private fun enqueueMockResponse(filename: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    /**
     * test fun de get top headlines, para ver si llega contenido y el request si es correceto
     */

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("NewsResponse.json")
            val response = newsApiService.getTopHeadlines(country = "us", page = 1).body()
            // que el response no sea null
            Truth.assertThat(response).isNotNull()
            // que el path del response si sea el que necesitamos, osea que se este haciendo el request bien

            val request = mockWebServer.takeRequest()
            Truth.assertThat(request.path)
                .isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=4cabda5f676a44819f45d390095f5d61")
        }

    }

    @Test
    fun getTopHeadlines_responseReceived_correctPageSize() {
        runBlocking {
            enqueueMockResponse("NewsResponse.json")
            val response = newsApiService.getTopHeadlines(country = "us", page = 1).body()
            val articleListSize = response!!.articles
            Truth.assertThat(articleListSize.size).isEqualTo(20)
        }

    }

    @Test
    fun getTopHeadlines_responseReceived_correctContent() {
        runBlocking {
            enqueueMockResponse("NewsResponse.json")
            val response = newsApiService.getTopHeadlines(country = "us", page = 1).body()
            val article = response!!.articles[0]
            Truth.assertThat(article.author)
                .isEqualTo("Andy Gregory")
            Truth.assertThat(article.url)
                .isEqualTo("https://www.independent.co.uk/news/world/americas/earthquake-peru-barranca-today-south-america-b1965517.html")
        }

    }
}