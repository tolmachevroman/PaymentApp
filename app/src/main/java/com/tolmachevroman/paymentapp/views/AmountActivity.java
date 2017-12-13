package com.tolmachevroman.paymentapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tolmachevroman.paymentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AmountActivity extends AppCompatActivity {

    @BindView(R.id.amountTxt)
    EditText amountTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        ButterKnife.bind(this);

        amountTxt.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onContinueClicked(null);
                    return true;
                }
                return false;
            }

        });
    }

    @OnClick(R.id.continueBtn)
    public void onContinueClicked(View view) {
        String amount = amountTxt.getText().toString();
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_input_amount_toast), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, PaymentMethodActivity.class);
            startActivity(intent);
        }
    }
}
