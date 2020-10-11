package com.vtb.hackathon.automarket;

import android.util.Log;
import com.vtb.hackathon.automarket.model.LoanCondition;
import okhttp3.*;

import java.io.IOException;

import static com.vtb.hackathon.automarket.MainActivity.LOG_TAG;

public class VTBApiRequest implements Runnable {
    private Thread thread;
    private String responseText;
    private int requestMode;
    private String encodedImage;
    private LoanCondition loanCondition;

    public static int REQUEST_MODE_MARKET_PLACE = 0;
    public static int REQUEST_MODE_CAR_RECOGNITION = 1;
    public static int REQUEST_MODE_CALCULATE_LOAN = 2;

    public VTBApiRequest(int requestMode) {
        thread = new Thread(this, String.valueOf(requestMode));
        this.requestMode = requestMode;
        responseText = "";
        //thread.start();
    }

    public VTBApiRequest() {
        this(REQUEST_MODE_MARKET_PLACE);
    }

    public VTBApiRequest(String encodedImage) {
        thread = new Thread(this, String.valueOf(REQUEST_MODE_CAR_RECOGNITION));
        responseText = "";
        this.requestMode = REQUEST_MODE_CAR_RECOGNITION;
        this.encodedImage = encodedImage;
    }

    public VTBApiRequest(LoanCondition loanCondition) {
        this(REQUEST_MODE_CALCULATE_LOAN);
        this.loanCondition = loanCondition;
    }

    @Override
    public void run() {
        if (requestMode == REQUEST_MODE_MARKET_PLACE) {
            this.responseText = requestMarketPlace();
        } else if (requestMode == REQUEST_MODE_CAR_RECOGNITION) {
            this.responseText = requestCarRecognition();
        } else if (requestMode == REQUEST_MODE_CALCULATE_LOAN) {
            this.responseText = requestLoanCalculation();
        }
    }

    private String requestMarketPlace() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gw.hackathon.vtb.ru/vtb/hackathon/marketplace")
                .get()
                .addHeader("x-ibm-client-id", "0453c8246026f77798fc0332a3d085d3")
                .addHeader("accept", "application/json")
                .build();

        String responseText = "";
        try {
            Response response = client.newCall(request).execute();
            responseText = response.body().string();
            System.out.println(responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseText;
    }

    private String requestCarRecognition() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        this.encodedImage = this.encodedImage.replace("\n", "");
        RequestBody body = RequestBody.create(mediaType, "{\"content\":\"" + this.encodedImage + "\"}");
        Request request = new Request.Builder()
                .url("https://gw.hackathon.vtb.ru/vtb/hackathon/car-recognize")
                .post(body)
                .addHeader("x-ibm-client-id", "0453c8246026f77798fc0332a3d085d3")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();

        String responseText = "";
        try {
            Response response = client.newCall(request).execute();
            responseText = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseText;
    }

    private String requestLoanCalculation() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        Log.d(LOG_TAG, String.valueOf(loanCondition.getCost()));
        Log.d(LOG_TAG, String.valueOf(loanCondition.getKaskoValue()));
        Log.d(LOG_TAG, String.valueOf(loanCondition.getResidualPayment()));
        Log.d(LOG_TAG, String.valueOf(loanCondition.getTerm()));
        Log.d(LOG_TAG, String.valueOf(loanCondition.getInitialFee()));

        RequestBody body = RequestBody.create(mediaType, "{\"clientTypes\":[\"ac43d7e4-cd8c-4f6f-b18a-5ccbc1356f75\"],\"cost\":" + loanCondition.getCost() + ",\"initialFee\":" + loanCondition.getInitialFee() + ",\"kaskoValue\":" + loanCondition.getKaskoValue() + ",\"language\":\""+ loanCondition.getLanguage() +"\",\"residualPayment\":" + loanCondition.getResidualPayment() + ",\"settingsName\":\"" + loanCondition.getSettingsName() +"\",\"specialConditions\":[\"57ba0183-5988-4137-86a6-3d30a4ed8dc9\",\"b907b476-5a26-4b25-b9c0-8091e9d5c65f\",\"cbfc4ef3-af70-4182-8cf6-e73f361d1e68\"],\"term\":"+ loanCondition.getTerm() + "}");
        Request request = new Request.Builder()
                .url("https://gw.hackathon.vtb.ru/vtb/hackathon/calculate")
                .post(body)
                .addHeader("x-ibm-client-id", "0453c8246026f77798fc0332a3d085d3")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();

        String responseText = "";
        try {
            Response response = client.newCall(request).execute();
            responseText = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "{\"clientTypes\":[\"ac43d7e4-cd8c-4f6f-b18a-5ccbc1356f75\"],\"cost\":" + loanCondition.getCost() + ",\"initialFee\":" + loanCondition.getInitialFee() + ",\"kaskoValue\":" + loanCondition.getKaskoValue() + ",\"language\":\""+ loanCondition.getLanguage() +"\",\"residualPayment\":" + loanCondition.getResidualPayment() + ",\"settingsName\":\"" + loanCondition.getSettingsName() +"\",\"specialConditions\":[\"57ba0183-5988-4137-86a6-3d30a4ed8dc9\",\"b907b476-5a26-4b25-b9c0-8091e9d5c65f\",\"cbfc4ef3-af70-4182-8cf6-e73f361d1e68\"],\"term\":"+ loanCondition.getTerm() + "}");
        return responseText;
    }

    public void startRequest() {
        thread.start();
    }

    public String getResponseText() {
        try {
            if (!thread.isInterrupted()) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseText;
    }
}
