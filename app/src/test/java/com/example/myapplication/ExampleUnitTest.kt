package com.example.myapplication

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import com.example.myapplication.model.db.AppDataBase
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.model.db.RecyclerItemDao
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ExampleUnitTest {

    lateinit var db: AppDataBase
    lateinit var dao: RecyclerItemDao
    val imageFilm1: String = "https://media.kitsu.io/anime/poster_images/2/medium.jpg"
    val nameTest1 = "name_test"
    val filmDetails1 = "details_test"
    val animeForTest1 = RecyclerItem(imageFilm1, nameTest1, filmDetails1, true)

    val imageFilm2: String = "https://media.kitsu.io/anime/poster_images/2/medium.jpg"
    val nameTest2 = "name_test_2"
    val filmDetails2 = "details_test_2"
    val animeForTest2 = RecyclerItem(imageFilm2, nameTest2, filmDetails2, true)

    val filmsList: MutableList<RecyclerItem> = ArrayList()


    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = db.recyclerItemDao
        filmsList.add(animeForTest1)
        filmsList.add(animeForTest2)
        dao.saveAll(filmsList)
    }

    @Test
    fun `проверка получения фильма из базы`() {
        val currentFilm = (dao.getAll()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10).get(0)
        assertEquals(animeForTest1, currentFilm)
    }

    @Test
    fun `проверка получения любимого фильма из базы`() {
        val niceFilm = (dao.getAllFavourites()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10).get(0)
        assertEquals(animeForTest1, niceFilm)
    }

    @Test
    fun `проверка на удаление и добавление фильма в избранное`() {
        dao.updateFavourites(nameTest1,0)
        val justFilm = (dao.getAll()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10).get(0).isFavorite
        assertFalse(justFilm)
        dao.updateFavourites(nameTest1,1)
        val niceFilm = (dao.getAll()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10).get(0).isFavorite
        assertTrue(niceFilm)
    }

    @Test
    fun `проверка получения фильмов из базы`() {
        val currentFilm = (dao.getAll()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10)
        assertEquals(filmsList, currentFilm)
    }

    @Test
    fun `проверка получения любимого фильмов из базы`() {
        val niceFilm = (dao.getAllFavourites()?.create() as LimitOffsetDataSource<RecyclerItem>).loadRange(0, 10)
        assertEquals(filmsList, niceFilm)
    }
}