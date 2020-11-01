package com.lupesoft.pruebadeingreso

import android.content.res.Resources
import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.google.common.base.CharMatcher.`is`
import com.lupesoft.pruebadeingreso.utilities.typeSearchViewText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import java.util.EnumSet.allOf

@HiltAndroidTest
class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    @Throws(InterruptedException::class)
    fun clickSearchUser() {
        onView(withId(R.id.search_user)).perform(click())
        onView(withId(R.id.search_user)).perform(typeSearchViewText("Leanne Graham"))
    }

    @Test
    @Throws(InterruptedException::class)
    fun clickItemUsers() {
        onView(withId(R.id.user_list)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.user_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.user_item)).check(ViewAssertions.matches(isDisplayed()))
    }
}