package com.tolmachevroman.paymentapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    static final int PAYMENT_METHOD_REQUEST = 100;

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
            //Clear text field if starts with zero
            String input = s.toString();
            if (input.length() > 0 && input.charAt(0) == '0') {
                s.replace(0, 1, "");
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYMENT_METHOD_REQUEST && resultCode == RESULT_OK) {
            showPaymentInfoDialog(data);
            amountTxt.setText("");
        }
    }

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
            startActivityForResult(intent, PAYMENT_METHOD_REQUEST);
        }
    }

    private void showPaymentInfoDialog(Intent data) {
        StringBuilder builder = new StringBuilder();
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                String value = (String) bundle.get(key);

                switch (key) {
                    case Constants.AMOUNT:
                        builder.append(String.format(getString(R.string.amount), value));
                        break;
                    case Constants.PAYMENT_METHOD_ID:
                        builder.append(String.format(getString(R.string.payment_method), value));
                        break;
                    case Constants.INSTALLMENT:
                        builder.append(String.format(getString(R.string.installment), value));
                        break;
                    case Constants.ISSUER_ID:
                        builder.append(String.format(getString(R.string.issuer), value));
                        break;
                }
                builder.append("\n");

            }
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(builder.toString());
        dialog.setCancelable(true);
        AlertDialog alert = dialog.create();
        alert.show();
    }
}
