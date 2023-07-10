package com.dicoding.todoapp.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import com.dicoding.todoapp.R

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
class TaskActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(TaskActivity::class.java)

    @Test
    fun testAddTaskButton() {
        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.add_task)).check(matches(isDisplayed()))
    }
}
