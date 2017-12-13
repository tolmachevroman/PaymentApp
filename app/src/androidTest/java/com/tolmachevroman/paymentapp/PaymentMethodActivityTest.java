package com.tolmachevroman.paymentapp;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tolmachevroman.paymentapp.utils.Constants;
import com.tolmachevroman.paymentapp.views.activities.BanksActivity;
import com.tolmachevroman.paymentapp.views.activities.PaymentMethodActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PaymentMethodActivityTest {

    @Rule
    public IntentsTestRule<PaymentMethodActivity> activityRule =
            new IntentsTestRule<PaymentMethodActivity>(PaymentMethodActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.AMOUNT, "1");
                    return intent;
                }
            };

    /**
     * Check that all UI items exist within the screen
     */
    @Test
    public void uiElementsExist() {
        onView(withId(R.id.label)).check(matches(isDisplayed()));
        onView(withId(R.id.paymentMethodsRcl)).check(matches(isDisplayed()));
    }

    /**
     * Check that clicking on Payment method starts BanksActivity
     */
    @Test
    public void clickOnPaymentMethodStartsBanksActivity() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.paymentMethodsRcl))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(hasComponent(BanksActivity.class.getName()));
    }

}
