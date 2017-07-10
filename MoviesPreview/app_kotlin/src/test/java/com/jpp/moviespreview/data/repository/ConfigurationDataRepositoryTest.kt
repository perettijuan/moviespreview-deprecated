package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.configuration.ConfigurationDataRepository
import com.jpp.moviespreview.data.datasource.ConfigurationDataSource
import com.jpp.moviespreview.data.model.ImagesConfiguration
import com.jpp.moviespreview.data.model.MoviesConfiguration
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by jpp on 6/26/17.
 */
class ConfigurationDataRepositoryTest {


    @Test fun test_lastKnownConfiguration_callsAllPossibleDataStores_andReturnsNullIfNoOneProvides() {
        val store1 = mock(ConfigurationDataSource::class.java)
        val store2 = mock(ConfigurationDataSource::class.java)
        val store3 = mock(ConfigurationDataSource::class.java)


        val subject = ConfigurationDataRepository(listOf(store1, store2, store3))

        assertNull(subject.lastKnownConfiguration())
        verify(store1).lastKnownConfiguration()
        verify(store2).lastKnownConfiguration()
        verify(store3).lastKnownConfiguration()
    }

    @Test fun test_lastKnownConfiguration_returnsFirstFound_andDoesNotCallAnyMore() {
        val store1 = mock(ConfigurationDataSource::class.java)
        val store2 = mock(ConfigurationDataSource::class.java)
        val store3 = mock(ConfigurationDataSource::class.java)
        val expected = MoviesConfiguration(ImagesConfiguration("someUrl", listOf()))

        `when`(store1.lastKnownConfiguration()).then { expected }

        val subject = ConfigurationDataRepository(listOf(store1, store2, store3))

        val result = subject.lastKnownConfiguration()

        assertNotNull(result)
        assertEquals(expected, result)
        verify(store1).lastKnownConfiguration()
        verify(store2, never()).lastKnownConfiguration()
        verify(store3, never()).lastKnownConfiguration()
    }

}