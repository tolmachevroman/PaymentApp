package com.tolmachevroman.paymentapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tolmachevroman.paymentapp.R;
import com.tolmachevroman.paymentapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AmountActivity extends AppCompatActivity {

    @BindView(R.id.amountTxt)
    EditText amountTxt;

    private TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = s.toString();
            if (input.length() > 0 && input.charAt(0) == '0') {
                s.replace(0, 1, "");
            }
        }
    };

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
        amountTxt.addTextChangedListener(amountTextWatcher);
    }

    @OnClick(R.id.continueBtn)
    public void onContinueClicked(View view) {
        String amount = amountTxt.getText().toString();
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_input_amount_toast), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, PaymentMethodActivity.class);
            intent.putExtra(Constants.AMOUNT, amount);
            startActivity(intent);
        }
    }
}
