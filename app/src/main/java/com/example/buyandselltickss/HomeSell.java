package com.example.buyandselltickss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyandselltickss.ScrapBoardScreen.DonHang;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeSell extends AppCompatActivity {
    Toolbar toolbar;
    Button btnThemDC;
    Button btnXacNhanBan;
    TextView diaChi;
    RadioButton radio1, radio2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sell);

        btnThemDC = findViewById(R.id.btnThemDC);
        toolbar = findViewById(R.id.toolbarsell);
        btnXacNhanBan = findViewById(R.id.btnXacNhanban);
        diaChi = findViewById(R.id.tvDiachiban);
        radio1= findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeMainActivity.class));
            }
        });
        btnThemDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeSell.this, Map.class);
                startActivity(intent);
            }
        });

        btnXacNhanBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanBan();
            }
        });

    }
        private void xacNhanBan() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myrefence = database.getReference("Danh Sach Don Hang");

            String diaChiBan = diaChi.getText().toString();
            String timeBan1 = radio1.getText().toString();
            String timeBan2 = radio2.getText().toString();

            DonHang hang = new DonHang();
            if(radio1.isChecked()) {
                 hang = new DonHang(diaChiBan,timeBan1);

            }
            if(radio2.isChecked()) {
                hang = new DonHang(diaChiBan,timeBan2);
            }
            String dinhDanh = String.valueOf(hang.getAddress());

            myrefence.child(dinhDanh).setValue(hang, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    Toast.makeText(HomeSell.this,"đã đặt đơn thanh công",Toast.LENGTH_LONG).show();
                }
            });




        }
}