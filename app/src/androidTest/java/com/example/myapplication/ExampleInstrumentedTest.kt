package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myapplication.view.activity.MainActivity
import com.example.myapplication.view.viewholders.RecyclerViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.

 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapplication", appContext.packageName)
        waitForCompleteApiCall()
        addFilmToFavourites()
        openFavoritesList()
        checkFavoritesList()
    }

    private fun waitForCompleteApiCall() {
        var count = 5
        while (count>0) {
            try {
                Espresso.onView(withId(R.id.fragment_main))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                count = 0
            } catch (ex: NoMatchingViewException) {
                Thread.sleep(500)
                count--
            }
        }
    }

    private fun addFilmToFavourites() {
        var count = 5
        while (count>0) {
            try {
                Espresso.onView(withId(R.id.fragment_main))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewHolder>(0,longClick()))
                count = 0
            } catch (ex: PerformException) {
                Thread.sleep(500)
                count--
            }
        }
    }

    private fun openFavoritesList() {
        var count = 5
        while (count>0) {
            try {
                Espresso.onView(withId(R.id.nav_favorites)).perform(click())
                Thread.sleep(1500)
                count = 0
            } catch (ex: PerformException) {
                Thread.sleep(500)
                count--
            }
        }
    }

    private fun checkFavoritesList() {
        var count = 5
        while (count>0) {
            try {
                Espresso.onView(withId(R.id.name_film_1)).check(matches(withText("Cowboy Bebop")));
                Thread.sleep(500)
                count = 0
            } catch (ex: PerformException) {
                Thread.sleep(500)
                count--
            }
        }
    }

}