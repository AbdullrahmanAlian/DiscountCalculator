package com.example.discountcalculator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Instrumented UI tests for {@link MainActivity}.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void initialScreen_displaysTitleAndCalculateButton() {
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCalculate)).check(matches(isDisplayed()));
    }

    @Test
    public void calculateDiscount_validInputs_displaysFinalPrice() {
        onView(withId(R.id.etPrice)).perform(replaceText("100"), closeSoftKeyboard());
        onView(withId(R.id.etDiscount)).perform(replaceText("20"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.tvResult)).check(matches(withText(containsString("80.00"))));
        onView(withId(R.id.tvResult)).check(matches(withText(containsString("20.00"))));
    }
}
