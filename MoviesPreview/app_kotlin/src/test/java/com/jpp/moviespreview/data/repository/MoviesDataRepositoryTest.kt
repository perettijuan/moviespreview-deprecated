package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.MoviesDataSource
import com.jpp.moviespreview.domain.model.MoviePage
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by jpp on 7/7/17.
 */
class MoviesDataRepositoryTest {

    @Test fun test_topRated_callsAllPossibleDataStores_andReturnsNullIfNoOneProvides() {
        val store1 = mock(MoviesDataSource::class.java)
        val store2 = mock(MoviesDataSource::class.java)
        val store3 = mock(MoviesDataSource::class.java)
        val pageId = 1

        val subject = MoviesDataRepository(listOf(store1, store2, store3))

        assertNull(subject.topRated(pageId))
        verify(store1).topRated(pageId)
        verify(store2).topRated(pageId)
        verify(store3).topRated(pageId)
    }


    @Test fun test_topRated_returnsFirstFound_andDoesNotCallAnyMore() {
        val store1 = mock(MoviesDataSource::class.java)
        val store2 = mock(MoviesDataSource::class.java)
        val store3 = mock(MoviesDataSource::class.java)
        val pageId = 1
        val expected = MoviePage(listOf(), 1, 500, 50)

        `when`(store2.topRated(pageId)).then { expected }

        val subject = MoviesDataRepository(listOf(store1, store2, store3))
        val result = subject.topRated(pageId)

        assertNotNull(result)
        assertEquals(expected, result)
        verify(store1).topRated(pageId)
        verify(store2).topRated(pageId)
        verify(store3, never()).topRated(pageId)
    }

    @Test fun test_inTheatres_callsAllPossibleDataStores_andReturnsNullIfNoOneProvides() {
        val store1 = mock(MoviesDataSource::class.java)
        val store2 = mock(MoviesDataSource::class.java)
        val store3 = mock(MoviesDataSource::class.java)
        val pageId = 1

        val subject = MoviesDataRepository(listOf(store1, store2, store3))

        assertNull(subject.inTheatres(pageId))
        verify(store1).inTheatres(pageId)
        verify(store2).inTheatres(pageId)
        verify(store3).inTheatres(pageId)
    }


    @Test fun test_inTheatres_returnsFirstFound_andDoesNotCallAnyMore() {
        val store1 = mock(MoviesDataSource::class.java)
        val store2 = mock(MoviesDataSource::class.java)
        val store3 = mock(MoviesDataSource::class.java)
        val pageId = 1
        val expected = MoviePage(listOf(), 1, 500, 50)

        `when`(store2.inTheatres(pageId)).then { expected }

        val subject = MoviesDataRepository(listOf(store1, store2, store3))
        val result = subject.inTheatres(pageId)

        assertNotNull(result)
        assertEquals(expected, result)
        verify(store1).inTheatres(pageId)
        verify(store2).inTheatres(pageId)
        verify(store3, never()).inTheatres(pageId)
    }



}