package com.vtb.hackathon.automarket;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.vtb.hackathon.automarket.model.CarBrand;
import com.vtb.hackathon.automarket.util.JSONParser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView mCarImageView;
    private Button mCameraButton;

    public static final int REQUEST_IMAGE_CAPTURE = 0;
    public static final int REQUEST_IMAGE_PICK = 1;
    public static final String GIF_MIME_TYPE = "image/gif";
    public static final String JPEG_MIME_TYPE = "image/jpeg";
    public static final String PNG_MIME_TYPE = "image/png";
    public static final String INTENT_CAR_MARKET_PLACE_RESPONSE = "INTENT_CAR_RECOGNIZE";
    public static final String INTENT_CAR_PROBABILITIES = "INTENT_CAR_PROBABILITIES";
    private Intent carInformationIntent;

    private VTBApiRequest thread;
    public static final String LOG_TAG = "myLogs";

    /*
     * @TODO Присутствие, цена, количество машин, количество цветов, страна
     * @TODO Количество дверей
     * @TODO Тип машины: лифтбэк, хэтчбэк, фургон
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCarImageView = findViewById(R.id.user_car_image_view);
        mCameraButton = findViewById(R.id.camera_button);

        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        thread = new VTBApiRequest();
        thread.startRequest();
        String response = thread.getResponseText();
        ArrayList<CarBrand> carBrands = JSONParser.parseMarketPlace(response);
        carInformationIntent = new Intent(this, CarInformationActivity.class);
        carInformationIntent.putExtra(INTENT_CAR_MARKET_PLACE_RESPONSE, response);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_IMAGE_CAPTURE) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mCarImageView.setImageBitmap(imageBitmap);

            String encodedText = encodeImage(imageBitmap);

            VTBApiRequest request = new VTBApiRequest(encodedText);
            request.startRequest();
            String response = request.getResponseText();
            carInformationIntent.putExtra(INTENT_CAR_PROBABILITIES, response);
            startActivity(carInformationIntent);
        }

    }

    private String encodeImage(Bitmap imageBitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    public static Bitmap.CompressFormat getCompressFormat(@NonNull String mimeType) {
        switch (mimeType) {
            case JPEG_MIME_TYPE:
                return Bitmap.CompressFormat.JPEG;
            case PNG_MIME_TYPE:
                return Bitmap.CompressFormat.PNG;
        }
        // default should be png
        return Bitmap.CompressFormat.PNG;
    }
}