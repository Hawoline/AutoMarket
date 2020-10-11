package com.vtb.hackathon.automarket;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.vtb.hackathon.automarket.model.CalcResult;
import com.vtb.hackathon.automarket.model.LoanCondition;
import com.vtb.hackathon.automarket.util.JSONParser;

import static com.vtb.hackathon.automarket.MainActivity.LOG_TAG;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mCarCostEditText;
    private EditText mInitialFeeEditText;
    private EditText mKaskoValueEditText;
    private EditText mResidualPaymentEditText;
    private EditText mTermEditText;
    private TextView mPaymentTextView;
    private TextView mLoanAmountTextView;
    private TextView mSubsidyTextView;
    private TextView mContractRateTextView;
    private TextView mLastPaymentTextView;
    private Button mCalculateButton;
    private Button mGetLoanButton;
    private LoanCondition mLoanCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mCarCostEditText = findViewById(R.id.car_cost_edit_text);
        mInitialFeeEditText = findViewById(R.id.initial_fee_edit_text);
        mKaskoValueEditText = findViewById(R.id.kasko_value_edit_text);
        mResidualPaymentEditText = findViewById(R.id.residual_payment_edit_text);
        mTermEditText = findViewById(R.id.term_edit_text);

        mPaymentTextView = findViewById(R.id.payment_text_view);
        mLoanAmountTextView = findViewById(R.id.loan_amount_text_view);
        mSubsidyTextView = findViewById(R.id.subsidy_text_view);
        mContractRateTextView = findViewById(R.id.contract_rate_text_view);
        mLastPaymentTextView = findViewById(R.id.last_payment_text_view);

        mCalculateButton = findViewById(R.id.calculate_button);
        mGetLoanButton = findViewById(R.id.get_loan_button);

        mLoanCondition = new LoanCondition();
        mCarCostEditText.setText(String.valueOf(mLoanCondition.getCost()));
        mInitialFeeEditText.setText(String.valueOf(mLoanCondition.getInitialFee()));
        mKaskoValueEditText.setText(String.valueOf(mLoanCondition.getKaskoValue()));
        mResidualPaymentEditText.setText(String.valueOf(mLoanCondition.getResidualPayment()));
        mTermEditText.setText(String.valueOf(mLoanCondition.getTerm()));

        mCarCostEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String string = mCarCostEditText.getText().toString();
                    double value = Double.parseDouble(string);
                    if (value >= 10_000_000 || value < mLoanCondition.getInitialFee()) {
                        value = 850000;
                    }
                    mLoanCondition.setCost(value);
                    return true;
                }
                return false;
            }
        });

        mInitialFeeEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String string = mInitialFeeEditText.getText().toString();
                    mLoanCondition.setInitialFee(Double.parseDouble(string));
                    Log.d(LOG_TAG, String.valueOf(mLoanCondition.getInitialFee()));
                    return true;
                }
                return false;
            }
        });
        mKaskoValueEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String string = mKaskoValueEditText.getText().toString();
                    try {
                        mLoanCondition.setKaskoValue(Integer.parseInt(string));
                    } catch (NumberFormatException e) {
                        mLoanCondition.setKaskoValue(0);
                    }
                    return true;
                }
                return false;
            }
        });
        mResidualPaymentEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String string = mResidualPaymentEditText.getText().toString();
                    mLoanCondition.setResidualPayment(Double.parseDouble(string));
                    return true;
                }
                return false;
            }
        });
        mTermEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String string = mTermEditText.getText().toString();
                    int term = Integer.parseInt(string);
                    if (term < 1 || term > 7){
                        term = 5;
                        mTermEditText.setText(String.valueOf(term));
                    }
                    mLoanCondition.setTerm(term);
                    return true;
                }
                return false;
            }
        });

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VTBApiRequest vtbApiRequest = new VTBApiRequest(mLoanCondition);
                vtbApiRequest.startRequest();
                String response = vtbApiRequest.getResponseText();
                Toast.makeText(getApplicationContext(), "Calculated", Toast.LENGTH_SHORT).show();
                CalcResult calcResult = JSONParser.parseCalcResult(response);
                mPaymentTextView.setText(String.valueOf(calcResult.getPayment()));
                mLoanAmountTextView.setText(String.valueOf(calcResult.getLoanAmount()));
                mSubsidyTextView.setText(String.valueOf(calcResult.getSubsidy()));
                mContractRateTextView.setText(String.valueOf(calcResult.getContractRate()));
                mLastPaymentTextView.setText(String.valueOf(calcResult.getLastPayment()));
            }
        });
    }
}