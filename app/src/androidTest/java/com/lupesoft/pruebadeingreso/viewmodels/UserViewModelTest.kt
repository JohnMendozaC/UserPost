package com.lupesoft.pruebadeingreso.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.pruebadeingreso.data.AppDataBase
import com.lupesoft.pruebadeingreso.data.Status
import com.lupesoft.pruebadeingreso.data.user.UserRepository
import com.lupesoft.pruebadeingreso.utilities.getValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
class UserViewModelTest {

    private lateinit var appDataBase: AppDataBase
    private lateinit var viewModel: UserViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        viewModel = UserViewModel(userRepository)
    }

    @After
    fun tearDown() {
        appDataBase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testLoadingService() {
        Assert.assertEquals(getValue(viewModel.users).status, Status.LOADING)
    }
}