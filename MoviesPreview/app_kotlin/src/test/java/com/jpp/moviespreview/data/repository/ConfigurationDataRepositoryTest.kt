package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataStore
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by jpp on 6/26/17.
 */
class ConfigurationDataRepositoryTest {


    @Test fun test_lastKnownConfiguration_callsAllPossibleDataStores_andReturnsNullIfNoOneProvides() {
        val store1 = mock(ConfigurationDataStore::class.java)
        val store2 = mock(ConfigurationDataStore::class.java)
        val store3 = mock(ConfigurationDataStore::class.java)

        val subject = ConfigurationDataRepository(listOf(store1, store2, store3))

        assertNull(subject.lastKnownConfiguration())
        verify(store1).lastKnownConfiguration()
        verify(store2).lastKnownConfiguration()
        verify(store3).lastKnownConfiguration()
    }

}