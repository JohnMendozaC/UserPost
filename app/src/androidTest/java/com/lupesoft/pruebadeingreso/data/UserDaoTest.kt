package com.lupesoft.pruebadeingreso.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.pruebadeingreso.data.user.UserDao
import com.lupesoft.pruebadeingreso.data.user.UserEntity
import com.lupesoft.pruebadeingreso.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: AppDataBase
    private lateinit var userDao: UserDao
    private val userA =
        UserEntity(1, "name1", "username1", "name1@correo.com", "3214574312", "www.name1.com")
    private val userB =
        UserEntity(2, "name2", "username2", "name2@correo.com", "3114574512", "www.name2.com")
    private val userC =
        UserEntity(3, "name3", "username3", "name3@correo.com", "3154544318", "www.name3.com")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        userDao = database.userDao()
        userDao.insertAll(listOf(userA, userB, userC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetUsers() {
        val userList = getValue(userDao.getUsersTest())
        assertThat(userList.size, equalTo(3))

        assertThat(userList[0], equalTo(userA))
        assertThat(userList[1], equalTo(userB))
        assertThat(userList[2], equalTo(userC))
    }

    @Test
    fun testGetUser() {
        assertThat(getValue(userDao.getUser(userA.id)), equalTo(userA))
    }

}


