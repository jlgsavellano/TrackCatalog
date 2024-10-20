package com.appetiser.trackcatalog.data.repository

import com.appetiser.trackcatalog.data.local.dao.TrackDao
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.data.remote.api.ApiService
import com.appetiser.trackcatalog.data.remote.response.TrackResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response
import org.junit.Assert.assertEquals

/**
 * Sample unit test for TrackRepository
 */
class TrackRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var dao: TrackDao

    private lateinit var repository: TrackRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = TrackRepository(apiService, dao)
    }

    @Test
    fun `should search tracks from API and insert all to database`() = runTest {
        val term = "star"
        val country = "au"
        val media = "movie"

        val mockTracks = listOf(
            Track(
                id = 292232038,
                name = "Win a Date With Tad Hamilton",
                artwork = "https://is1-ssl.mzstatic.com/image/thumb/Video/51/c0/20/mzi.bgiimxvp.jpg/100x100bb.jpg",
                price = 14.99,
                genre = "Comedy",
                description = "Famous Hollywood actor Tad Hamilton (Josh Duhamel) is trying to promote his new movie. His manager (Sean Hayes) and his agent (Nathan Lane) both convince him to participate in a dating contest in order to improve his bad-boy image. The contest is won by Rosalee Futch (Kate Bosworth), an attractive young checkout girl who works at a Piggly Wiggly in West Virginia. When Tad ends up falling in love with her, he's willing to give up big-city life and move to small-town America. Meanwhile, her best friend and co-worker Pete (Topher Grace) is finally motivated to reveal his secret crush on her. Rosalee finds herself in the middle of a love triangle between her closest friend and a dream date.",
                country = "AUS",
                currency = "AUD"
            )
        )

        val mockResponse = TrackResponse(
            count = 1,
            result = mockTracks
        )

        `when`(apiService.search(term, country, media)).thenReturn(Response.success(mockResponse))

        val result = repository.searchTracks(term, country)

        assertEquals(mockTracks, result)
        verify(dao).insertAll(mockTracks)
    }

    @Test
    fun `should get all track from database`() = runTest {
        val country = "au"

        val mockTracks = listOf(
            Track(
                id = 292232038,
                name = "Win a Date With Tad Hamilton",
                artwork = "https://is1-ssl.mzstatic.com/image/thumb/Video/51/c0/20/mzi.bgiimxvp.jpg/100x100bb.jpg",
                price = 14.99,
                genre = "Comedy",
                description = "Famous Hollywood actor Tad Hamilton (Josh Duhamel) is trying to promote his new movie. His manager (Sean Hayes) and his agent (Nathan Lane) both convince him to participate in a dating contest in order to improve his bad-boy image. The contest is won by Rosalee Futch (Kate Bosworth), an attractive young checkout girl who works at a Piggly Wiggly in West Virginia. When Tad ends up falling in love with her, he's willing to give up big-city life and move to small-town America. Meanwhile, her best friend and co-worker Pete (Topher Grace) is finally motivated to reveal his secret crush on her. Rosalee finds herself in the middle of a love triangle between her closest friend and a dream date.",
                country = "AUS",
                currency = "AUD"
            )
        )

        `when`(dao.getAllTracks("%$country%")).thenReturn(mockTracks)

        val result = repository.getAllTracks(country)

        assertEquals(mockTracks, result)
        verify(dao).getAllTracks("%$country%")
    }

    @Test
    fun `should get track from database`() = runTest {
        val trackId = 292232038L

        val mockTrack = Track(
            id = 292232038L,
            name = "Win a Date With Tad Hamilton",
            artwork = "https://is1-ssl.mzstatic.com/image/thumb/Video/51/c0/20/mzi.bgiimxvp.jpg/100x100bb.jpg",
            price = 14.99,
            genre = "Comedy",
            description = "Famous Hollywood actor Tad Hamilton (Josh Duhamel) is trying to promote his new movie. His manager (Sean Hayes) and his agent (Nathan Lane) both convince him to participate in a dating contest in order to improve his bad-boy image. The contest is won by Rosalee Futch (Kate Bosworth), an attractive young checkout girl who works at a Piggly Wiggly in West Virginia. When Tad ends up falling in love with her, he's willing to give up big-city life and move to small-town America. Meanwhile, her best friend and co-worker Pete (Topher Grace) is finally motivated to reveal his secret crush on her. Rosalee finds herself in the middle of a love triangle between her closest friend and a dream date.",
            country = "AUS",
            currency = "AUD"
        )

        `when`(dao.getTrack(trackId)).thenReturn(mockTrack)

        val result = repository.getTrack(trackId)

        assertEquals(mockTrack, result)
        verify(dao).getTrack(trackId)
    }

    @Test
    fun `should update track favorite in database`() = runTest {
        val mockTrack = Track(
            id = 292232038L,
            name = "Win a Date With Tad Hamilton",
            artwork = "https://is1-ssl.mzstatic.com/image/thumb/Video/51/c0/20/mzi.bgiimxvp.jpg/100x100bb.jpg",
            price = 14.99,
            genre = "Comedy",
            description = "Famous Hollywood actor Tad Hamilton (Josh Duhamel) is trying to promote his new movie. His manager (Sean Hayes) and his agent (Nathan Lane) both convince him to participate in a dating contest in order to improve his bad-boy image. The contest is won by Rosalee Futch (Kate Bosworth), an attractive young checkout girl who works at a Piggly Wiggly in West Virginia. When Tad ends up falling in love with her, he's willing to give up big-city life and move to small-town America. Meanwhile, her best friend and co-worker Pete (Topher Grace) is finally motivated to reveal his secret crush on her. Rosalee finds herself in the middle of a love triangle between her closest friend and a dream date.",
            country = "AUS",
            currency = "AUD",
            isFavorite = true
        )

        repository.setFavoriteTrack(mockTrack)

        verify(dao).updateTrack(mockTrack)
    }
}