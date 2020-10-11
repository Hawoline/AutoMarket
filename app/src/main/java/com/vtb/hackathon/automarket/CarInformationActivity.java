package com.vtb.hackathon.automarket;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import com.vtb.hackathon.automarket.model.CarBrand;
import com.vtb.hackathon.automarket.model.CarModel;
import com.vtb.hackathon.automarket.util.JSONParser;

import java.util.ArrayList;

import static com.vtb.hackathon.automarket.MainActivity.LOG_TAG;

public class CarInformationActivity extends AppCompatActivity {
    private ImageView mCarImageView;
    private TextView mCarModelTextView;
    private TextView mTransportTypeTextView;
    private TextView mPresenceTextView;
    private TextView mMinPriceTextView;
    private TextView mCarCountTextView;
    private TextView mColorCountTextView;
    private TextView mCountryCodeTextView;
    private Button mGetCreditConditionsButton;

    private int mMinPrice;

    public static final String INTENT_MIN_PRICE = "INTENT_MIN_PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        mCarImageView = findViewById(R.id.car_image_view);
        mCarModelTextView = findViewById(R.id.car_model_text_view);
        mTransportTypeTextView = findViewById(R.id.transport_type_text_view);
        mPresenceTextView = findViewById(R.id.presence_text_view);
        mMinPriceTextView = findViewById(R.id.min_price_text_view);
        mCarCountTextView = findViewById(R.id.car_count_text_view);
        mColorCountTextView = findViewById(R.id.color_count_text_view);
        mCountryCodeTextView = findViewById(R.id.country_text_view);
        mGetCreditConditionsButton = findViewById(R.id.get_credit_conditions_button);

        mGetCreditConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                intent.putExtra(INTENT_MIN_PRICE, mMinPrice);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String probabilitiesResponse = intent.getStringExtra(MainActivity.INTENT_CAR_PROBABILITIES);
        Pair<String, Double> maxProbability = JSONParser.parseCarRecognition(probabilitiesResponse);

        Log.d(LOG_TAG, String.valueOf(maxProbability.getSecond()));
        if (maxProbability.getSecond() < 0.5) {
            mGetCreditConditionsButton.setEnabled(false);
            Toast.makeText(this, "Car not recognized", Toast.LENGTH_LONG).show();

        } else {
            mGetCreditConditionsButton.setEnabled(true);
            String response = intent.getStringExtra(MainActivity.INTENT_CAR_MARKET_PLACE_RESPONSE);
            ArrayList<CarBrand> carBrands = JSONParser.parseMarketPlace(response);
            Log.d(LOG_TAG, maxProbability.getFirst());

            //Picasso.with(this).load(carBrands.get(0).getModels()[0].getPhoto()).into(mCarImageView);
            brands:
            for (CarBrand carBrand : carBrands) {
                CarModel[] carModels = carBrand.getModels();
                String[] probabilityBrandAndModelTitle = maxProbability.getFirst().split(" ");
                if(carBrand.getTitle().equals(probabilityBrandAndModelTitle[0])) {
                    boolean carModelIsFound = false;
                    for (int carModelIndex = 0; carModelIndex < carModels.length; carModelIndex++) {
                        CarModel carModel = carModels[carModelIndex];
                        String[] carModelTitleWords = carModel.getTitle().split(" ");
                        if (probabilityBrandAndModelTitle[0].equals("BMW")) {
                            if ((probabilityBrandAndModelTitle[1] + " серии").equals(carModel.getTitle())) {
                                carModelIsFound = true;
                            }
                        } else {
                            if (probabilityBrandAndModelTitle[probabilityBrandAndModelTitle.length - 1].equals(carModelTitleWords[carModelTitleWords.length - 1])) {

                                Log.d(LOG_TAG, carModel.getTitle());
                                carModelIsFound = true;
                            }
                        }

                        if (carModelIsFound) {
                            Picasso.with(this).load(carBrand.getModels()[carModelIndex].getPhoto()).into(mCarImageView);
                            mCarModelTextView.setText(carModel.getTitle());
                            mTransportTypeTextView.setText(carModel.getTransportType().getTitle());
                            mPresenceTextView.setText(carModel.isAbsentee() ? getString(R.string.absent) : getString(R.string.presence));
                            mMinPriceTextView.setText(String.valueOf(carModel.getMinPrice()));
                            mCarCountTextView.setText(String.valueOf(carModel.getCount()));
                            mColorCountTextView.setText(String.valueOf(carModel.getColorsCount()));
                            mCountryCodeTextView.setText(carBrand.getCountry().getCode());
                            break brands;
                        }
                    }
                }
            }
        }
    }
}