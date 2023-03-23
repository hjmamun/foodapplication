package com.example.android.navdrawerexperiment;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class PlaceOrder extends AppCompatActivity {


    private TextView mChosenTimeTextView;
    EditText nameEditText, addressEditText, phoneEditText, noteEditText;
    Button timePickButton;
    TextView productName;
    DataHelper dataHelper;
    String itemName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        dataHelper = new DataHelper();

        mChosenTimeTextView = findViewById(R.id.timeDisplay);
        nameEditText = findViewById(R.id.editText1);
        addressEditText = findViewById(R.id.editText2);
        phoneEditText = findViewById(R.id.editText3);
        noteEditText = findViewById(R.id.editText4);
        productName = findViewById(R.id.productName);

        timePickButton = findViewById(R.id.timePickerButton);

        Log.d(TAG, "onCreate: " + dataHelper.productList().size());


        if (dataHelper.productList().size() > 0) {
            for (int i = 0; i < dataHelper.productList().size(); i++) {
                itemName += dataHelper.productList().get(i) + "\n";

            }
            productName.setText(itemName);
        } else {
            productName.setText("Not Product");
        }

        timePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(PlaceOrder.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hoursOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("k: mm a");
                        String time = dateFormat.format(calendar.getTime());
                        mChosenTimeTextView.setText(time);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

    }


}