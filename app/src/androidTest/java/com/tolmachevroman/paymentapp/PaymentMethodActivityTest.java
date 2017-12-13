package com.tolmachevroman.paymentapp;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tolmachevroman.paymentapp.views.PaymentMethodActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PaymentMethodActivityTest {

    @Rule
    public IntentsTestRule<PaymentMethodActivity> activityRule =
            new IntentsTestRule<>(PaymentMethodActivity.class);

    /**
     * Check that all UI items exist within the screen
     */
    @Test
    public void uiElementsExist() {
        onView(withId(R.id.label)).check(matches(isDisplayed()));
        onView(withId(R.id.paymentMethodsRcl)).check(matches(isDisplayed()));
    }

}
