package com.vtb.hackathon.automarket.util;

import android.util.Log;
import com.vtb.hackathon.automarket.Pair;
import com.vtb.hackathon.automarket.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.vtb.hackathon.automarket.MainActivity.LOG_TAG;
/*

*/
public class JSONParser {
    public static final String[] brands = {
            "BMW 3",
            "BMW 5",
            "Cadillac ESCALADE",
            "Chevrolet Tahoe",
            "Jaguar F-PACE",
            "KIA K5",
            "KIA Optima",
            "KIA Sportage",
            "Land Rover RANGE ROVER VELAR",
            "Mazda 6"
    };
    public static Pair<String, Double> parseCarRecognition(String response) {
        Pair<String, Double> maxProbability = new Pair<>("", 0.0);
        try {
            JSONObject probabilityJSON = new JSONObject(response);

            JSONObject probabilitiesJSONObject = probabilityJSON.getJSONObject("probabilities");
            Probabilities probabilities;
            Pair<String, Double>[] probabilityPairs = new Pair[10];
            for (int i = 0; i < brands.length; i++) {
                probabilityPairs[i] = new Pair<>(brands[i], probabilitiesJSONObject.getDouble(brands[i]));
            }
            probabilities = new Probabilities(probabilityPairs);
            maxProbability = probabilities.getMaxProbability();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maxProbability;
    }

    public static ArrayList<CarBrand> parseMarketPlace(String jsonText) {
        ArrayList<CarBrand> carBrands = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray listArray = jsonObject.getJSONArray("list");
            for (int carBrandIndex = 0; carBrandIndex < listArray.length(); carBrandIndex++) {
                JSONObject carBrandObject = listArray.getJSONObject(carBrandIndex);
                JSONObject countryObject = carBrandObject.getJSONObject("country");
                JSONArray carModelArray = carBrandObject.getJSONArray("models");

                ArrayList<CarModel> carModels = new ArrayList<>();
                Country country = new Country(countryObject.getString("code"),
                        countryObject.getInt("id"),
                        countryObject.getString("title")
                );

                CarModel[] carModelsArray = {};

                for (int carModelIndex = 0; carModelIndex < carModelArray.length(); carModelIndex++) {
                    JSONObject carModelObject = carModelArray.getJSONObject(carModelIndex);

                    JSONObject transportTypeObject = carModelObject.getJSONObject("transportType");

                    TransportType transportType = new TransportType(
                            transportTypeObject.getString("alias"),
                            transportTypeObject.getInt("id"),
                            transportTypeObject.getString("title")
                    );
                    CarModel carModel = new CarModel(
                            carModelObject.getBoolean("absentee"),
                            carModelObject.getString("alias"),
                            carModelObject.getInt("id"),
                            carModelObject.getString("prefix"),
                            carModelObject.getString("title"),
                            carModelObject.getString("titleRus"),
                            carModelObject.getInt("specmetallicPay"),
                            carModelObject.getString("photo"),
                            carModelObject.getInt("pearlPay"),
                            carModelObject.getInt("metallicPay"),
                            transportType,
                            carModelObject.getString("ownTitle"),
                            carModelObject.getInt("minPrice"),
                            carModelObject.getInt("count"),
                            carModelObject.getInt("colorsCount"),
                            carModelObject.getString("carId")
                    );

                    carModels.add(carModel);
                }

                carBrands.add(
                        new CarBrand(
                                carBrandObject.getBoolean("absentee"),
                                carBrandObject.getString("alias"),
                                country,
                                carBrandObject.getInt("currentCarCount"),
                                carBrandObject.getInt("currentModelsTotal"),
                                new String[0],
                                carBrandObject.getInt("id"),
                                carBrandObject.getBoolean("isOutbound"),
                                carBrandObject.getString("logo"),
                                carModels.toArray(carModelsArray),
                                carBrandObject.getString("title"),
                                carBrandObject.getString("titleRus")
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return carBrands;
    }

    public static CalcResult parseCalcResult(String response) {
        CalcResult calcResult;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject resultObject = jsonObject.getJSONObject("result");
            double subsidy = 0;
            double lastPayment = 0;
            if (!resultObject.isNull("subsidy")) {
                subsidy = resultObject.getDouble("subsidy");
            }
            if (!resultObject.isNull("lastPayment")){
                lastPayment = resultObject.getDouble("lastPayment");
            }
            calcResult = new CalcResult(
                    resultObject.getDouble("payment"),
                    resultObject.getDouble("loanAmount"),
                    subsidy,
                    resultObject.getDouble("contractRate"),
                    lastPayment
            );
        } catch (JSONException e) {
            calcResult = new CalcResult();
            Log.d(LOG_TAG, response);
            e.printStackTrace();
        }

        return calcResult;
    }
}
