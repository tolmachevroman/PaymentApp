package com.tolmachevroman.paymentapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tolmachevroman.paymentapp.views.AmountActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AmountActivityTest {

    @Rule
    public ActivityTestRule<AmountActivity> activityRule =
            new ActivityTestRule<>(AmountActivity.class, true, true);

    /**
     * Check that all UI items exist within the screen
     */
    @Test
    public void uiElementsExist() {
        onView(withId(R.id.amountTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.continueBtn)).check(matches(isDisplayed()));
    }

    /**
     * Check that Toast is shown if amount is empty
     */
    @Test
    public void shouldShowToastIfAmountIsEmpty() {
        onView(withId(R.id.continueBtn)).perform(click());
        onView(withText(R.string.input_amount_toast)).
                inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
}
