package com.tolmachevroman.paymentapp;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tolmachevroman.paymentapp.utils.Constants;
import com.tolmachevroman.paymentapp.views.activities.AmountActivity;
import com.tolmachevroman.paymentapp.views.activities.PaymentMethodActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AmountActivityTest {

    @Rule
    public IntentsTestRule<AmountActivity> activityRule =
            new IntentsTestRule<>(AmountActivity.class);

    /**
     * Check that all UI items exist within the screen
     */
    @Test
    public void uiElementsExist() {
        onView(withId(R.id.label)).check(matches(isDisplayed()));
        onView(withId(R.id.amountTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.continueBtn)).check(matches(isDisplayed()));
    }

    /**
     * Check that Toast is shown if amount is empty
     */
    @Test
    public void shouldShowToastIfAmountIsEmpty() {
        onView(withId(R.id.continueBtn)).perform(click());
        onView(withText(R.string.please_input_amount_toast)).
                inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    /**
     * Check that amount cannot start with zero
     */
    @Test
    public void amountCannotStartWithZero() {
        onView(withId(R.id.amountTxt)).perform(typeText("0"));
        onView(withId(R.id.amountTxt)).check(matches(withText("")));
    }

    /**
     * Check that non zero amount successfully starts PaymentMethodActivity, with amount in extras
     */
    @Test
    public void nonZeroAmountStartsPaymentMethodActivity() {
        onView(withId(R.id.amountTxt)).perform(typeText("1"), pressImeActionButton());
        intended(allOf(hasExtra(Constants.AMOUNT, "1"), hasComponent(PaymentMethodActivity.class.getName())));
    }
}
