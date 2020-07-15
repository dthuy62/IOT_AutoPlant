package com.example.iot_autoplant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private TextView soil_moisture, txtThongBao;
    DatabaseReference reference;
    String status;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                status = snapshot.child("Do am dat").getValue().toString();
                soil_moisture.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggleButton.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference("Status");
                    databaseReference.setValue(1);
                    txtThongBao.setText("Máy bơm đang bật");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference("Status");
                    databaseReference.setValue(0);
                    txtThongBao.setText("Máy bơm đang tắt");
                }
            }
        };
        toggleButton.setOnCheckedChangeListener(listener);
    }

    private void AnhXa() {
        soil_moisture = findViewById(R.id.soil_moisture);
        toggleButton = findViewById(R.id.ClickMayBom);
        txtThongBao = findViewById(R.id.textViewThongBao);
        toggleButton.setChecked(false);
    }
}


//        on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = database.getReference("Status");
//                databaseReference.setValue(1);
//            }
//        });
//        off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = database.getReference("Status");
//                databaseReference.setValue(0);
//            }
//        });
//        SwitchCompat switchCompat = findViewById(R.id.switch_pump);
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                int value = isChecked ? 1 : 0;
//                FirebaseDatabase.getInstance().getReference().child("Status").setValue(value);
//            }
//        });

