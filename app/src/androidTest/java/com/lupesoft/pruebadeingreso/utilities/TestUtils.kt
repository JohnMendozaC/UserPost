package com.lupesoft.pruebadeingreso.utilities

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.lupesoft.pruebadeingreso.data.user.UserVo
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf


/**
 * [Plant] objects used for tests.
 */
val testUsers = arrayListOf(
    UserVo(1, "name1", "username1", "name1@correo.com", "3214574312", "www.name1.com"),
    UserVo(2, "name2", "username2", "name2@correo.com", "3114574512", "www.name2.com"),
    UserVo(3, "name3", "username3", "name3@correo.com", "3154544318", "www.name3.com")
)
val testUser = testUsers[0]

fun typeSearchViewText(text: String?): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            //Ensure that only apply if it is a SearchView and if it is visible.
            return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
        }

        override fun getDescription(): String {
            return "replace text"
        }

        override fun perform(uiController: UiController, view: View) {
            (view as SearchView).setQuery(text, true)
        }
    }
}