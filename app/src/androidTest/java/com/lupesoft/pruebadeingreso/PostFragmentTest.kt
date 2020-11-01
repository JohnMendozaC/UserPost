package com.lupesoft.pruebadeingreso

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lupesoft.pruebadeingreso.utilities.testUser
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostFragmentTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToPostFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putParcelable("user", testUser) }
                findNavController(R.id.nav_host).navigate(
                    R.id.action_userListFragment_to_userPostsFragment,
                    bundle
                )
            }
        }
    }

    @Ignore("Press see posts")
    @Test
    fun clickSearchUser() {
        onView(ViewMatchers.withId(R.id.user_posts_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}