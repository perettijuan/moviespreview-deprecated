package com.jpp.moviespreview.data.server.datasource

import com.jpp.moviespreview.data.server.ImagesConfiguration
import com.jpp.moviespreview.data.server.MoviesConfiguration
import com.jpp.moviespreview.data.server.retrofit.MoviesPreviewApi
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Response

/**
 * Created by jpp on 6/27/17.
 */
class ServerConfigurationDataSourceTest {


    @Test fun lastKnownConfiguration_callsApi_andMapsResult() {
        val apiInstance = mock(MoviesPreviewApi::class.java)
        val apiKey = "apiKey"
        val call = mock(Call::class.java)
        val response = mock(Response::class.java)
        val expectedResponse = mock(MoviesConfiguration::class.java)
        val imagesConfiguration = mock(ImagesConfiguration::class.java)
        val expectedSizes = listOf("size1", "size2")
        val expectedUrl = "someUrl"

        `when`(apiInstance.getLastConfiguration(apiKey)).then { call }
        `when`(call.execute()).then { response }
        `when`(response.body()).then { expectedResponse }
        `when`(expectedResponse.images).then { imagesConfiguration }
        `when`(imagesConfiguration.base_url).then { expectedUrl }
        `when`(imagesConfiguration.poster_sizes).then { expectedSizes }

        val subject = ServerConfigurationDataSource(apiInstance, apiKey)
        val actualResponse = subject.lastKnownConfiguration()

        verify(apiInstance).getLastConfiguration(apiKey)
        assertNotNull(actualResponse)
        assertNotNull(actualResponse!!.imagesConfiguration)
        assertEquals(expectedUrl, actualResponse.imagesConfiguration.baseUrl)
        assertEquals(expectedSizes, actualResponse.imagesConfiguration.sizes)
    }

}